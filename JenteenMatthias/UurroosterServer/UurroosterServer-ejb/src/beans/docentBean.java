/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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

/**
 *
 * @author witmoca
 */
@Stateless
public class docentBean implements docentBeanLocal {
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
                if(vMap.containsKey(studd))
                    vMap.put(studd, relS.getRelatie());
            }
            
            for(UrsStudentrelatie relC : relsCol){
                UrsGebruiker collega = commonBean.getGebruiker(relC.getUrsStudentrelatiePK().getCollega());
                if( vMap.containsKey(collega) ){
                    if(vMap.get(collega) < relC.getRelatie())
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
    
    // STATUS
    /**
     * Alle Studenten verliezen de mogelijkheid om te kiezen
     */
    @Override
    public void eindeKeuzes(){
        em.createNamedQuery("UrsStudent.updateStatusEindeKeuze").executeUpdate();
    }    
}
