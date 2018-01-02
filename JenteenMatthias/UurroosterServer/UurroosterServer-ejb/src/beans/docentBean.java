/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pakket.UrsGebruiker;
import pakket.UrsKlas;
import pakket.UrsStudent;
import pakket.UrsStudentrelatie;
import pakket.UrsStudentrelatiePK;

/**
 *
 * @author witmoca
 */
@Stateless
public class docentBean implements docentBeanLocal {
    @EJB
    private studentBeanLocal studentBean;
    @EJB
    private commonBeanLocal commonBean;
    @PersistenceContext(unitName = "UurroosterServer-ejbPU")
    private EntityManager em;

    // KLAS
    /** Selecteer alle klassen
     * 
     * @return lijst met klassen
     */
    @Override
    public List<UrsKlas> getKlasLijst(){
        Query q = em.createNamedQuery("UrsKlas.findAll");
        return (List<UrsKlas>) q.getResultList();
    }
    
    /** Selecteer alle klassen (en map hun warnings aantal daarbij)
     * 
     * @return lijst met klassen
     */
    @Override
    public Map<UrsKlas, Integer> getKlasLijstMetWarnings(){
        List<UrsKlas> klassen = (List<UrsKlas>) em.createNamedQuery("UrsKlas.findAll").getResultList();
        Map<UrsKlas, Integer> klasMap = new HashMap<UrsKlas, Integer>();
        for(UrsKlas uk : klassen){
            klasMap.put(uk, this.getViolatedRelaties(uk.getKlasid()).size());
        }
        return klasMap;
    }
    
    /** Vertaal klasId naar UrsKlas object
     * 
     * @param klasid verwijzend naar een klas
     * @return UrsKlas object van die klas
     */
    @Override
    public UrsKlas getKlas(int klasid){
        Query q = em.createNamedQuery("UrsKlas.findByKlasid");
        q.setParameter("klasid", klasid);
        return (UrsKlas)q.getSingleResult(); 
    }

    /** Nieuwe klas aanmaken
     *
     * Indien de klas al bestaat, of de klasNaam is leeg
     * dan is deze functie een nuloperatie
     * 
     * @param klasNaam
     */
    @Override
    public void addKlas(String klasNaam){
        if(klasNaam.trim().equalsIgnoreCase("")){return;}
        Integer klasid = (Integer) em.createNamedQuery("UrsKlas.findMaxKlasid").getSingleResult();
        if(klasid == null){
            klasid = 0;
        }
        
        // Negeer als klas al bestaat
        Query qExists = em.createNamedQuery("UrsKlas.findByNaam");
        qExists.setParameter("naam",klasNaam);
        if(!qExists.getResultList().isEmpty())
            return;
        em.persist(new UrsKlas(klasid+1, klasNaam, 0));
    }
    /** Verwijder klas (en studenten uit die klas zetten)
     * 
     * @param klasId 
     */
    @Override
    public void removeKlas(int klasId){
        // Verwijder studenten uit klas
        Query nq = em.createNamedQuery("UrsStudent.setKlasToNull");
        nq.setParameter("klasid", this.getKlas(klasId));
        nq.executeUpdate();
        
        // Verwijder klas
        Query q = em.createNamedQuery("UrsKlas.removeByKlasid");
        q.setParameter("klasid", klasId);
        q.executeUpdate();
    }
    
    /** Verander de naam van de klas
     * Doet niets wanneer de nieuwe naam al ingebruik is
     *
     * @param klasId
     * @param nieuweNaam
     */
    @Override
    public void changeKlasNaam(int klasId, String nieuweNaam){
        UrsKlas klas = this.getKlas(klasId);
        
        if(em.createNamedQuery("UrsKlas.findByNaam").setParameter("naam", nieuweNaam).getResultList().size() != 0)
            return;
        
        klas.setNaam(nieuweNaam);
        em.persist(klas);
    }
    
    // STUDENTEN IN KLAS
    
    /** Zoek alle studenten in een klas
     * 
     * @param klasId id van de klas
     * @return Lijst van UrsStudent die alle studenten uit die klas bevat
     */
    @Override
    public List<UrsStudent> getStudentenInKlas(int klasId){
        Query q = em.createNamedQuery("UrsStudent.findStudentByKlas");
        q.setParameter("klasid", this.getKlas(klasId));
        return (List<UrsStudent>) q.getResultList();
    }
    
    /** Zelfde als getStudentenInKlas, maar dan gemaped op aantal problemen
     *
     * @param klasId
     * @return
     */
    @Override
    public Map<UrsStudent,Integer> getErroredStudentenInKlas(int klasId){
        List<UrsStudent> studenten = this.getStudentenInKlas(klasId);
        Map<UrsStudent,Integer> studMap = new HashMap<>();
        // copy lijst in map met init waarde
        for(UrsStudent student : studenten){
            studMap.put(student, 0);
        }

        List<UrsStudentrelatie> rel = this.getViolatedRelaties(klasId);
        for(UrsStudentrelatie r : rel){
            UrsStudent collegaSt = studentBean.getStudent(r.getUrsStudentrelatiePK().getCollega());
            UrsStudent studentSt = studentBean.getStudent(r.getUrsStudentrelatiePK().getStudent());
            if(studMap.containsKey(studentSt)){
                studMap.put(studentSt, studMap.get(studentSt) + 1);
            }
            if(studMap.containsKey(collegaSt)){
                studMap.put(collegaSt, studMap.get(collegaSt) + 1);
            }
        }
        return studMap;
    }
    
    /** Zoek alle studenten die nog niet in een klas zitten
     * Gesorteerd op voorkeur (Een relatie NIET heeft voorrang op WEL)
     * 
     *
     * @param klasId
     * @return Map van UrsGebruikers met Integers als relatie (0 = neutraal, 1 = VOORKEUR, 2 = NIET)
     */
    @Override
    public Map<UrsGebruiker, Integer> getKlaslozeStudentenVoorkeur(int klasId){
        Map<UrsGebruiker, Integer> vMap = new HashMap<UrsGebruiker, Integer>();
        List<UrsStudent> studentenInKlas = this.getStudentenInKlas(klasId);
        List<UrsStudent> zonderKlasStu = (List<UrsStudent>) em.createNamedQuery("UrsStudent.findStudentZonderKlas").getResultList();
        // Geen relatie
        for(UrsStudent stud : zonderKlasStu){
            vMap.put(stud.getUrsGebruiker(), 0);
        }
        
        for(UrsStudent stud : studentenInKlas){
            List<UrsStudentrelatie> relsStud = (List<UrsStudentrelatie>) stud.getUrsGebruiker().getUrsStudentrelatieCollection();
            List<UrsStudentrelatie> relsCol = (List<UrsStudentrelatie>) stud.getUrsGebruiker().getUrsStudentrelatieCollection1();
            
            for(UrsStudentrelatie relS : relsStud){
                UrsGebruiker studd = commonBean.getGebruiker(relS.getUrsStudentrelatiePK().getStudent());
                if(vMap.containsKey(studd) && vMap.get(studd) < relS.getRelatie()){
                    vMap.put(studd, relS.getRelatie());
                }
            }
            
            for(UrsStudentrelatie relC : relsCol){
                UrsGebruiker collega = commonBean.getGebruiker(relC.getUrsStudentrelatiePK().getCollega());
                if( vMap.containsKey(collega)&& vMap.get(collega) < relC.getRelatie()){
                    vMap.put(collega,relC.getRelatie());   
                }
            }
        }
        return vMap;
    }
    
    
    /** Schrijft de student in een bepaalde klas in
     * Indien de klas -1 is, dan wordt de student een klasloze student
     */
    @Override
    public void setStudentKlas(int userId, int klasId) {
        Query q = em.createNamedQuery("UrsStudent.setStudentKlas");
        q.setParameter("userid",userId);
        q.setParameter("klasid",klasId == -1 ? null : this.getKlas(klasId));
        q.executeUpdate();
    }
    
    /** Maak een lijst van relaties die verbroken zijn in deze klas
     *
     * @param klasId
     * @return
     */
    @Override
    public List<UrsStudentrelatie> getViolatedRelaties(int klasId){
        List<UrsStudent> studenten =  getStudentenInKlas(klasId);
        List<UrsStudentrelatie> rels = this.cleanRelPrioriteiten(em.createNamedQuery("UrsStudentrelatie.findAll").getResultList());
        List<UrsStudentrelatie> errors = new ArrayList<UrsStudentrelatie>();
        
        for(UrsStudentrelatie rel : rels){
            UrsStudent collegaSt = studentBean.getStudent(rel.getUrsStudentrelatiePK().getCollega());
            UrsStudent studentSt = studentBean.getStudent(rel.getUrsStudentrelatiePK().getStudent());
            
            // 1 : ofwel alle 2 erin, ofwel geen van de 2
            // 2 : relatie mag niet bestaan
            if( (rel.getRelatie() == 1 && (studenten.contains(collegaSt) ^ studenten.contains(studentSt))) ||
                (rel.getRelatie() == 2 && (studenten.contains(collegaSt) &&  studenten.contains(studentSt))) )
                    errors.add(rel); 
        }
        
        return errors;
    }
    
    /** Maakt een convenience klasse om gemakkelijk te kunnen afdrukken in jsp
     *
     * @param li
     * @return
     */
    @Override
    public List<RelatieWrapper> wrapRelaties(List<UrsStudentrelatie> li){
        List<RelatieWrapper> rw  = new ArrayList<>();
        for(UrsStudentrelatie rl : li){
            rw.add(new RelatieWrapper(commonBean.getUserName(rl.getUrsStudentrelatiePK().getStudent()),commonBean.getUserName(rl.getUrsStudentrelatiePK().getCollega() ), rl.getRelatie() ));
        }
        return rw;
    }
    
    /** Verwijder alle dubbele paren ([student collega] = [collega student]) uit de lijst
     *  en check en pas de relatie prioriteit aan
     * @param rels
     * @return 
     */
    public List<UrsStudentrelatie> cleanRelPrioriteiten(List<UrsStudentrelatie> rels){
        // Converteer naar relatieMap
        Map<UrsStudentrelatiePK, Integer> relsMap = new HashMap<UrsStudentrelatiePK, Integer>();
        for(UrsStudentrelatie r : rels){
            int student = r.getUrsStudentrelatiePK().getStudent();
            int collega = r.getUrsStudentrelatiePK().getCollega();
            // switch zodat student altijd kleiner is dan collega
            if(collega < student){
                int temp = collega;
                collega = student;
                student = temp;
            }
            UrsStudentrelatiePK primaryKey = new UrsStudentrelatiePK(student,collega);
            
            if(relsMap.containsKey(primaryKey)){ 
                Integer prev = relsMap.get(primaryKey);
                if(prev < r.getRelatie())
                    relsMap.put(primaryKey, r.getRelatie());
            } else {
                relsMap.put(primaryKey,r.getRelatie()); 
            }
        }
        
        // Herconverteer
        List<UrsStudentrelatie> result = new ArrayList<UrsStudentrelatie>();
        for(UrsStudentrelatiePK upk : relsMap.keySet()){
            result.add(new UrsStudentrelatie(upk, relsMap.get(upk)));
        }
        return result;
    }
    
    // STATUS
    /**
     * Alle Studenten verliezen de mogelijkheid om te kiezen
     */
    @Override
    public void eindeKeuzes(){
        em.createNamedQuery("UrsStudent.updateStatusEindeKeuze").executeUpdate();
    }  
    
    // STATUS
    /**
     * Alle Studenten verliezen de mogelijkheid om te kiezen
     * @return -1 als fout, anders 0
     */
    @Override
    public int bevestigen(){
        if(this.aantalStudenten() != this.aantalToegewezenStudenten())
            return -1;
        em.createNamedQuery("UrsStudent.updateStatusBevestigd").executeUpdate();
        return 0;
    } 
    
    /**
     *
     * @return
     */
    @Override
    public int periodeGestopt(){
        Query q = em.createNamedQuery("UrsStudent.findByStatus");
        q.setParameter("status",0);
        if(!q.getResultList().isEmpty())
            return -1;
        q.setParameter("status",1);
        if(!q.getResultList().isEmpty())
            return -1;
        q.setParameter("status",3);
        if(!q.getResultList().isEmpty())
            return -1;
        return 0;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int bevestigd(){
        Query q = em.createNamedQuery("UrsStudent.findByStatus");
        q.setParameter("status",0);
        if(!q.getResultList().isEmpty())
            return -1;
        q.setParameter("status",1);
        if(!q.getResultList().isEmpty())
            return -1;
        q.setParameter("status",2);
        if(!q.getResultList().isEmpty())
            return -1;
        return 0;
    } 
    
    
    @Override
    public int aantalToegewezenStudenten(){
        Query q = em.createNamedQuery("UrsStudent.countKlasNotNull");
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
    
    @Override
    public int aantalStudenten(){
        Query q = em.createNamedQuery("UrsStudent.countStudenten");
        return Integer.parseInt(q.getSingleResult().toString());
    }
}
