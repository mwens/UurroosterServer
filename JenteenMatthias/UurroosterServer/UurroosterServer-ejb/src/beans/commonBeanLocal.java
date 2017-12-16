/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import pakket.UrsGebruiker;
import pakket.UrsKlas;
import pakket.UrsStudent;

/**
 *
 * @author witmoca
 */
@Local
public interface commonBeanLocal {
    public int getUserId(String userNaam);
    public String getUserName(int userId);

    public List<UrsGebruiker> getStudentenLijst(String naam);

    public List<UrsKlas> getKlasLijst();

    public void addKlas();

    public void removeKlas(int klasId);

    public List<UrsStudent> getKlasStudenten(Integer klasId);

    public List<UrsStudent> getOverigeStudenten();

    public UrsKlas getKlas(int klasid);

    public void updateKlas(UrsKlas klas, int userId);

    public void eindeKeuzes();
}
