/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Local;
import pakket.UrsGebruiker;
import pakket.UrsStudent;
import pakket.UrsStudentrelatie;

/**
 *
 * @author witmoca
 */
@Local
public interface studentBeanLocal {
    // STATUS
    public int getStatus(int userId);
    public void setStatus(int userId, int status);

    // RELATIES
    public List<UrsStudentrelatie> getRelaties(int userId);
    public UrsStudentrelatie getRelatie(int userId, int collegaId); 
    public void setRelatie(int userId, int collegaId, int relatie);
    
    // STUDENTEN
    public List<UrsGebruiker> getAndereStudenten(String naam);
    public UrsStudent getStudent(int userId);   
}
