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
import pakket.UrsStudent;
import pakket.UrsStudentrelatie;

/**
 *
relatie toevoegen (met check op al aanwezig, als er conflicten zijn dan heeft NIET voorrang op WEL)
relatie verwijderen op basis van id

 * @author witmoca
 */
@Stateless
public class studentBean implements studentBeanLocal {
    @PersistenceContext(unitName = "UurroosterServer-ejbPU")
    private EntityManager em;
    
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
     * @param userId de userId van de student
     * @return UrsStudent voorstelling
     */
    @Override
    public UrsStudent getStudent(int userId){
        Query q = em.createNamedQuery("UrsStudent.findByUserid");
        q.setParameter("userid", userId);
        return (UrsStudent) q.getSingleResult();
    }
}
