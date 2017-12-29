/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class studentBean implements studentBeanLocal {
    @PersistenceContext(unitName = "UurroosterServer-ejbPU")
    private EntityManager em;
    
    // STATUS
    
    /** status ophalen op basis van id
     *
     * @param userId de userId van de student
     * @return status van student
     */
    @Override
    public int getStatus(int userId){
        return this.getStudent(userId).getStatus();
    }
    
    /** status veranderen op basis van id
     *
     * @param userId de userId van de student
     * @param status de nieuwe status
     */
    @Override
    public void setStatus(int userId, int status){
        Query q = em.createNamedQuery("UrsStudent.updateStatus");
        q.setParameter("userid",userId);
        q.setParameter("status",status);
        q.executeUpdate();
    }
    
    
    
    // RELATIES
    
    /** relaties ophalen op basis van id (enkel relaties die user zelf heeft gemaakt)
     *
     * @param userId id van student
     * @return lijst van studentrelaties die de student zelf heeft bepaald
     */
    @Override
    public List<UrsStudentrelatie> getRelaties(int userId){
        Query q = em.createNamedQuery("UrsStudentrelatie.findByStudent");
        q.setParameter("student",userId);
        List<UrsStudentrelatie> l = q.getResultList();
        return l;
    }
    
     /**
     * 
     * @param userId student die een relatie heeft met iemand
     * @param collegaId student met wie de relatie gevormd wordt   
     * @return student relatie object of null als de relatie niet bestaat
     */
    @Override
    public UrsStudentrelatie getRelatie(int userId, int collegaId){
        Query q = em.createNamedQuery("UrsStudentrelatie.findByPk");
        q.setParameter("student",userId);
        q.setParameter("collega",collegaId);
        try{
            return (UrsStudentrelatie) q.getSingleResult();
        } catch (NoResultException e) {  
        }
        return null;
    }
    
    /** relatie toevoegen
     *
     * @param userId student die een relatie heeft met iemand
     * @param collegaId student met wie de relatie gevormd wordt   
     * @param relatie status van de relatie
     */
    public void addRelatie(int userId, int collegaId, int relatie){
        if(userId == collegaId)
            return;
        UrsStudentrelatie usr = new UrsStudentrelatie(userId, collegaId, relatie);
        em.persist(usr);
    }
    
    /** relatie verwijderen
     *
     * @param userId
     * @param collegaId
     */
    public void deleteRelatie(int userId, int collegaId){
        Query q = em.createNamedQuery("UrsStudentrelatie.deleteByPk");
        q.setParameter("student",userId);
        q.setParameter("collega",collegaId);
        q.executeUpdate();
    }
    
    /** relatie toevoegen, aanpassen of verwijderen
     *
     * @param userId student die een relatie heeft met iemand
     * @param collegaId student met wie de relatie gevormd wordt   
     * @param relatie staat waarnaartoe wordt veranderd/toegevoegd (0 indien moet worden verwijderd)
     */
    @Override
    public void setRelatie(int userId, int collegaId, int relatie){
        UrsStudentrelatie usr = this.getRelatie(userId, collegaId);
        
        // Case: verwijder onbestaande
        if(usr == null && relatie == 0)
            return;
        
        // Case: verwijder bestaande
        if(relatie == 0){
            this.deleteRelatie(userId, collegaId);
            return;
        }
        
        // Case: add onbestaande
        if(usr == null){
            this.addRelatie(userId, collegaId, relatie);
            return;
        }
        
        // Case: pas bstaande aan
        Query q = em.createNamedQuery("UrsStudentrelatie.updateRelatie");
        q.setParameter("relatie",relatie);
        q.setParameter("student", userId);
        q.setParameter("collega", collegaId);
        q.executeUpdate();
    }
    
    // STUDENTEN
    
    /**
     *
     * @param userId de userId van de student
     * @return UrsStudent object
     */
    public UrsStudent getStudent(int userId){
        Query q = em.createNamedQuery("UrsStudent.findByUserid");
        q.setParameter("userid", userId);
        return (UrsStudent) q.getSingleResult();
    }

     
    /** Een lijst van alle studententen ophalen
     * 
     * @param naam
     * @return Lijst van studenten
     */
    @Override
    public List<UrsGebruiker> getAndereStudenten(String naam){
        Query q = em.createNamedQuery("UrsGebruiker.findOthersByGroep");
        q.setParameter("groep", "student");
        q.setParameter("naam", naam);
        return (List<UrsGebruiker>) q.getResultList();
    }
}
