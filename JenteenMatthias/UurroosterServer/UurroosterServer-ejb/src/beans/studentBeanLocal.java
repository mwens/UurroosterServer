/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Local;
import pakket.UrsStudent;

/**
 *
 * @author witmoca
 */
@Local
public interface studentBeanLocal {

    public UrsStudent getStudent(int userId);

    public int getStatus(int userId);

    public void setStatus(int userId, int status);
    
}
