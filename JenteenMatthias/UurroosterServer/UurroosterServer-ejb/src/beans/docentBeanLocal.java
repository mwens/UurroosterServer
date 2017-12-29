/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import pakket.UrsGebruiker;
import pakket.UrsKlas;
import pakket.UrsStudent;

/**
 *
 * @author witmoca
 */
@Local
public interface docentBeanLocal {
    // KLAS
    public List<UrsKlas> getKlasLijst();
    public UrsKlas getKlas(int klasid);
    public void addKlas(String klasNaam);
    public void removeKlas(int klasId);
    
    // STUDENTEN IN KLAS
    public List<UrsStudent> getStudentenInKlas(int klasId);
    public List<UrsStudent> getKlaslozeStudenten();
    public Map<UrsGebruiker, Integer> getKlaslozeStudentenVoorkeur(int klasId);
    public void setStudentKlas(int userId, int klasId);
    
    // STATUS
    public void eindeKeuzes();

    public int aantalToegewezenStudenten();

    public int aantalStudenten();
}
