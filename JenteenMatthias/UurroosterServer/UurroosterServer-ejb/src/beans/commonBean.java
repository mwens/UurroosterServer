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
    
    @Override
    public void addKlas(){
        int klasid = 0;
        Query q = em.createNamedQuery("UrsKlas.findKlasid");
        try{
            klasid = (int) q.getSingleResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        UrsKlas usr = new UrsKlas(klasid+1, "Groep " + Integer.toString(klasid+1), 0);
        em.persist(usr);
    }
    
    @Override
    public List<UrsKlas> getKlasLijst(){
        Query q = em.createNamedQuery("UrsKlas.findAll");
        List<UrsKlas> klassen = (List<UrsKlas>) q.getResultList();
        for(int i=0;i<klassen.size();i++){
            if(klassen.get(i).getKlasid() == 0)
                klassen.remove(i);
        }
        return klassen;
    }
    
    @Override
    public void removeKlas(int klasId){
        Query q = em.createNamedQuery("UrsKlas.removeByKlasid");
        q.setParameter("klasid", klasId);
        q.executeUpdate();
    }
    
    @Override
    public List<UrsStudent> getKlasStudenten(Integer klasId){
        Query q = em.createNamedQuery("UrsGebruiker.findStudentByKlas");
        q.setParameter("klasid", klasId);
        List<UrsStudent> studenten = (List<UrsStudent>) q.getResultList();
        return studenten;
    }
    
    @Override
    public List<UrsStudent> getOverigeStudenten(){
        Query q = em.createNamedQuery("UrsGebruiker.findStudentByKlas");
        q.setParameter("klasid", 0);
        List<UrsStudent> studenten = (List<UrsStudent>) q.getResultList();
        return studenten;
    }
    
    @Override
    public UrsKlas getKlas(int klasid){
        Query q = em.createNamedQuery("UrsKlas.findByKlasid");
        q.setParameter("klasid", klasid);
        return (UrsKlas)q.getSingleResult(); 
    }
    
    @Override
    public void updateKlas(UrsKlas klas, int userId){
        Query q = em.createNamedQuery("UrsStudent.updateKlas");
        q.setParameter("userid",userId);
        q.setParameter("klasid",klas);
        q.executeUpdate();
    }
    
    @Override
    public void eindeKeuzes(){
        Query q = em.createNamedQuery("UrsStudent.updateStatusEindeKeuze");
        q.executeUpdate();
    }
}
