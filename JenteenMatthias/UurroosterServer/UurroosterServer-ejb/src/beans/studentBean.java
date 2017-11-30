/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 

status ophalen op basis van id
status veranderen op basis van id

relaties ophalen op basis van id (enkel relaties die user zelf heeft gemaakt)
relatie toevoegen (met check op al aanwezig, als er conflicten zijn dan heeft NIET voorrang op WEL)
relatie verwijderen op basis van id

 * @author witmoca
 */
@Stateless
public class studentBean implements studentBeanLocal {
    @PersistenceContext(unitName = "UurroosterServer-ejbPU")
    private EntityManager em;
    
}
