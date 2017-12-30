/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Witmoca
 */
public class RelatieWrapper {
    private final String STUDENT_NAAM_1;
    private final String STUDENT_NAAM_2;
    private final int RELATIE;
    
    public RelatieWrapper(String stu1, String stu2, int rel){
        RELATIE = rel;
        STUDENT_NAAM_2 = stu2;
        STUDENT_NAAM_1 = stu1;
    }
    
    
    public String getSTUDENT_NAAM_1() {
        return STUDENT_NAAM_1;
    }

    public String getSTUDENT_NAAM_2() {
        return STUDENT_NAAM_2;
    }

    public int getRELATIE() {
        return RELATIE;
    }
}
