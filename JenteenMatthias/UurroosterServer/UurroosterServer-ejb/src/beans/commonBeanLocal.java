/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import javax.ejb.Local;
import pakket.UrsGebruiker;
import pakket.UrsKlas;

/**
 *
 * @author witmoca
 */
@Local
public interface commonBeanLocal {
    public int getUserId(String userNaam);
    public String getUserName(int userId);
    public UrsGebruiker getGebruiker(int userId);

    public ArrayList<String> getKlasStudenten(UrsKlas klas);

    public int wijzigWW(int userId, String old, String new1, String new2);

    public void resetWW(int userId, String new1);
}
