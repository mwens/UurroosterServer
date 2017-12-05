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
        
        
        int userId = commonBean.getUserId(request.getUserPrincipal().getName());
        if(userId == -1)
            gotoPage("Error.jsp", request, response);
        HttpSession sessie = request.getSession();   

        
        
        /*Vanaf hier stuk nieuwe code matthias*/
        
        List<UrsGebruiker> studenten = commonBean.getStudentenLijst(request.getUserPrincipal().getName());
        sessie.setAttribute("studenten", studenten);
        String stage = request.getParameter("stage");
        if(stage == null){
            stage = "";
        }
        System.out.println(stage);
        if(stage.equals("verwijderen")){
            System.out.println(request.getParameter("SelectedStudent"));
            int collegaId = commonBean.getUserId(request.getParameter("verwijderStudent"));
            if(collegaId != -1){
                studentBean.setRelatie(userId,collegaId, 0);
                System.out.println("relatie verwijderd X");
            }
        }
        if(stage.equals("wel")){
            System.out.println(request.getParameter("SelectedStudent"));
            int collegaId = commonBean.getUserId(request.getParameter("SelectedStudent"));
            if(collegaId != -1){
                studentBean.setRelatie(userId,collegaId, 1);
                System.out.println("relatie toegevoegd V");
            }
        }
        if(stage.equals("niet")){
            System.out.println(request.getParameter("SelectedStudent"));
            int collegaId = commonBean.getUserId(request.getParameter("SelectedStudent"));
            if(collegaId != -1){
                studentBean.setRelatie(userId,collegaId, 2);
                System.out.println("relatie toegevoegd X");
            }
        }
       
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
        System.out.println("1: " + welCollegas.size());
        System.out.println("2: " + nietCollegas.size());
        
        sessie.setAttribute("studentenRelatiesWel", welCollegas);
        sessie.setAttribute("studentenRelatiesNiet", nietCollegas);
        
        gotoPage("/student/student.jsp",request, response);
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
