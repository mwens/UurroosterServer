/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import beans.commonBeanLocal;
import java.io.IOException;
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
public class Controller extends HttpServlet {

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
        String stage = request.getParameter("stage");
        if("afmelden".equals(stage)){
            HttpSession sessie = request.getSession();
            try{
                sessie.invalidate();
            } catch (NullPointerException e){
                // Error/implementation difference in apache tomcat (catalina) workaround
            }
            gotoPage("/logout.jsp", request, response);
            return;
        }
        
        // PASSWOORD CHANGE DEEL
        HttpSession sessie = request.getSession();
        sessie.setAttribute("alert_ww", -2);
        if("ww".equals(stage)){
            int userId = commonBean.getUserId(request.getUserPrincipal().getName());
            int alert_ww = commonBean.wijzigWW(userId, request.getParameter("password_old"), request.getParameter("password_new"), request.getParameter("password_new2"));
            sessie.setAttribute("alert_ww", alert_ww);
            if(alert_ww != 1){
                gotoPage("/changeww.jsp", request, response);
                return;
            }
        } else if("wachtwoordwijzigen".equalsIgnoreCase(stage)){
            gotoPage("/changeww.jsp", request, response);
            return;
        }
        
        // STUUR NAAR INDIVIDUELE CONTROLLERS
        if (request.isUserInRole("student")){
            gotoPage("/student.do",request, response);
            return;
        }
        
        if (request.isUserInRole("docent")){
            gotoPage("/docent.do",request, response);
            return;
        }
         
        gotoPage("/common/index.jsp", request, response);
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
