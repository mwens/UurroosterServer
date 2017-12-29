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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pakket.UrsGebruiker;
import pakket.UrsKlas;
import pakket.UrsStudent;

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
        try{
            Query q = em.createNamedQuery("UrsGebruiker.findByNaam");
            q.setParameter("naam", userNaam);
            UrsGebruiker user = (UrsGebruiker) q.getSingleResult();
            return user.getUserid();
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
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
    
    /** Zoek het UrsGebruiker object gerelateerd aan userId
     *
     * @param userId
     * @return
     */
    @Override
    public UrsGebruiker getGebruiker (int userId){
        Query q = em.createNamedQuery("UrsGebruiker.findByUserid");
        q.setParameter("userid", userId);
        return (UrsGebruiker) q.getSingleResult();
    }
    
        /**
     *
     * @param klas
     * @return Alle studenten in de klas van de huidige student
     */
    @Override
    public ArrayList<String> getKlasStudenten(UrsKlas klas){
        ArrayList<String> result;
        Query q = em.createNamedQuery("UrsStudent.findStudentByKlas");
        q.setParameter("klasid", klas);
        List<UrsStudent> ursstudenten = (List<UrsStudent>) q.getResultList();
        result = new ArrayList<>();
        for(int i=0;i<ursstudenten.size();i++)
            result.add(this.getUserName(ursstudenten.get(i).getUserid()));
        return result;
    }
}
