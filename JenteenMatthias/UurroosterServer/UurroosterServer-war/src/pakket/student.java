/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import beans.commonBeanLocal;
import beans.studentBeanLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author witmoca
 */
public class student extends HttpServlet {
    @EJB
    private studentBeanLocal studentBean;
    @EJB
    private commonBeanLocal commonBean;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String gotop = "/student/student.jsp";
        
        int userId = commonBean.getUserId(request.getUserPrincipal().getName());
        if(userId == -1)
            gotoPage("Error.jsp", request, response);
        HttpSession sessie = request.getSession();   
        sessie.setAttribute("bevestigknop", "");
        sessie.setAttribute("studentnaam",request.getUserPrincipal().getName());
        int userStatus = studentBean.getStatus(userId);
        System.out.println(userStatus);
        switch(userStatus){
            case 0:
                gotop = kiesStudent(gotop,userId,sessie,request,response);
                break;
            case 2:
            case 1:
                gotop = overzichtStudent(userId,sessie,request,response);
                break;
            case 3:
                gotop = bevestigdeKlas(userId,sessie,request,response);
                break;
        }
        String stage = request.getParameter("stage");
        sessie.setAttribute("alert_ww", -2);
        if(stage == null)
            stage="";
        else if(stage.equals("ww")){
            int alert_ww = commonBean.wijzigWW(userId, request.getParameter("password_old"), request.getParameter("password_new"), request.getParameter("password_new2"));
            sessie.setAttribute("alert_ww", alert_ww);
            if(alert_ww != 1)
                gotop = "/student/changeww.jsp";
        }
        else if(stage.equals("wachtwoordwijzigen"))
            gotop = "/student/changeww.jsp";
        
        
        // Stel nodige info op voor student .jsp

        List<UrsStudentrelatie> l = studentBean.getRelaties(userId);
        
        List<String> welCollegas = new ArrayList<String>();
        List<String> nietCollegas = new ArrayList<String>();
        
        for(UrsStudentrelatie usr : l){
            if(usr.getRelatie() == 1)
                welCollegas.add(commonBean.getUserName(usr.getUrsStudentrelatiePK().getCollega()));
            if(usr.getRelatie() == 2)
                nietCollegas.add(commonBean.getUserName(usr.getUrsStudentrelatiePK().getCollega()));
        }
        
        sessie.setAttribute("studentenRelatiesWel", welCollegas);
        sessie.setAttribute("studentenRelatiesNiet", nietCollegas);
        
        gotoPage(gotop,request, response);
    }
    
public String overzichtStudent(int userId, HttpSession sessie, HttpServletRequest request, HttpServletResponse response){
    String stage = request.getParameter("stage");
    if(stage == null)
        stage="";
    else if(stage.equals("annuleren") && studentBean.getStatus(userId)==1){
        studentBean.setStatus(userId, 0);
        return "/student/student.jsp";
    }
    else if(stage.equals("bevestigen") && studentBean.getStatus(userId)==1)
        studentBean.setStatus(userId, 2);
    if(studentBean.getStatus(userId) == 2)
        sessie.setAttribute("bevestigknop", "disabled");
    return "/student/bevestigen.jsp";
}

public String bevestigdeKlas(int userId, HttpSession sessie, HttpServletRequest request, HttpServletResponse response){
    String stage = request.getParameter("stage");
    if(stage == null)
        stage = "";
    UrsStudent student = studentBean.getStudent(userId);
    UrsKlas klas = student.getKlasid();
    sessie.setAttribute("klasnaam", klas.getNaam());
    sessie.setAttribute("klas", commonBean.getKlasStudenten(klas));
    return "/student/klas.jsp";
}
    
 public String kiesStudent(String gotop,int userId, HttpSession sessie, HttpServletRequest request, HttpServletResponse response){
    List<UrsGebruiker> studenten = studentBean.getAndereStudenten(request.getUserPrincipal().getName());
    sessie.setAttribute("studenten", studenten);
    String stage = request.getParameter("stage");
    if(stage == null)
        stage = "";
    else switch (stage) {
        case "verwijderen":
            {
                int collegaId = commonBean.getUserId(request.getParameter("verwijderStudent"));
                if(collegaId != -1)
                    studentBean.setRelatie(userId,collegaId, 0);
                break;
            }
        case "wel":
            {
                int collegaId = commonBean.getUserId(request.getParameter("SelectedStudent"));
                if(collegaId != -1)
                    studentBean.setRelatie(userId,collegaId, 1);
                break;
            }
        case "niet":
            {
                int collegaId = commonBean.getUserId(request.getParameter("SelectedStudent"));
                if(collegaId != -1)
                    studentBean.setRelatie(userId,collegaId, 2);
                break;
            }
        case "bevestigen":
            studentBean.setStatus(userId, 1);
            gotop = "student/bevestigen.jsp";
            break;
        default:
            break;
    }
    return gotop;
}
    

    public void gotoPage(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher view = request.getRequestDispatcher(jsp);
        view.forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
