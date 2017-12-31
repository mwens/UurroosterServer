/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pakket.UrsKlas;
import pakket.UrsStudent;

/**
 *
 * @author witmoca
 */
@Stateless
public class printBean implements printBeanRemote {
    @EJB
    private docentBeanLocal docentBean;
    @PersistenceContext(unitName = "UurroosterServer-ejbPU")
    private EntityManager em;

    /** Get lijst van klasnamen
     *
     * @return
     */
    @Override
    public List<String> getKlassen() {
        List<UrsKlas> klasList = docentBean.getKlasLijst();
        List<String> result = new ArrayList<String>();
        for(UrsKlas klas : klasList)
            result.add(klas.getNaam());
        return result;
    }

    /** Get lijst van studentnamen in een bepaalde klas
     *  De lijst is leeg indien de klas niet bestaat
     *
     * @param klas
     * @return
     */
    @Override
    public List<String> getStudentenInKlas(String klas) {
        List<String> result = new ArrayList<String>();
        
        Query q = em.createNamedQuery("UrsKlas.findByNaam");
        q.setParameter("naam", klas);
        
        UrsKlas uKlas = null;
        try{
            uKlas = (UrsKlas) q.getSingleResult();            
        } catch (NoResultException e){
            return result;
        }
        
        List<UrsStudent> studenten = docentBean.getStudentenInKlas(uKlas.getKlasid());
        for(UrsStudent stud : studenten){
            result.add(stud.getUrsGebruiker().getNaam());
        }
        
        return result;
    }
    
    
}
