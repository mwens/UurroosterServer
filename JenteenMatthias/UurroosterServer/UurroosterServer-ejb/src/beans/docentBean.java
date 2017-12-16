/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pakket.UrsKlas;
import pakket.UrsStudent;

/**
 *
 * @author witmoca
 */
@Stateless
public class docentBean implements docentBeanLocal {
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
     * 
     * @param klasNaam
     */
    @Override
    public void addKlas(String klasNaam){
        Query q = em.createNamedQuery("UrsKlas.findMaxKlasid");
        int klasid = (int) q.getSingleResult();
        UrsKlas usr = new UrsKlas(klasid+1, klasNaam, 0);
        em.persist(usr);
    }
    /**
     * 
     * @param klasId 
     * @deprecated setKlas
     */
    @Override
    public void removeKlas(int klasId){
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
     * 
     * @return Lijst van UrsStudent die alle studenten niet in een klas bevat
     */
    @Override
    public List<UrsStudent> getKlaslozeStudenten(){
        return (List<UrsStudent>) em.createNamedQuery("UrsStudent.findStudentZonderKlas").getResultList();
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
