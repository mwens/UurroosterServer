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
import pakket.UrsGebruiker;

/**
 * @author witmoca
 */
@Stateless
public class commonBean implements commonBeanLocal {
    @PersistenceContext(unitName = "UurroosterServer-ejbPU")
    private EntityManager em;

    /** id ophalen op basis van de naam
     * 
     * @param userNaam naam van gebruiker
     * @return id van gebruiker
     */
    @Override
    public int getUserId(String userNaam){
        Query q = em.createNamedQuery("UrsGebruiker.findByNaam");
        q.setParameter("naam", userNaam);
        UrsGebruiker user = (UrsGebruiker) q.getSingleResult();
        return user.getUserid();
    }
    
    /** naam ophalen op basis van id
     *
     * @param userId id van gebruiker
     * @return naam van gebruiker
     */
    @Override
    public String getUserName(int userId){
        Query q = em.createNamedQuery("UrsGebruiker.findByUserid");
        q.setParameter("userid", userId);
        UrsGebruiker user = (UrsGebruiker) q.getSingleResult();
        return user.getNaam();
    }
    
    /** Een lijst van alle studententen ophalen
     * 
     * @param naam
     * @return Lijst van studenten
     */
    @Override
    public List<UrsGebruiker> getStudentenLijst(String naam){
        Query q = em.createNamedQuery("UrsGebruiker.findOthersByGroep");
        q.setParameter("groep", "student");
        q.setParameter("naam", naam);
        List<UrsGebruiker> studenten = (List<UrsGebruiker>) q.getResultList();
        return studenten;
    }
}
