/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Local;

/**
 *
 * @author witmoca
 */
@Local
public interface commonBeanLocal {
    public int getUserId(String userNaam);
    public String getUserName(int userId);
}
