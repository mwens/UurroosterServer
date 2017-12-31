/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author witmoca
 */
@Remote
public interface printBeanRemote {
    
    public List<String> getKlassen();
    public List<String> getStudentenInKlas(String klas);
}
