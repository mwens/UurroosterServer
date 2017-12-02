/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import beans.commonBeanLocal;
import beans.studentBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
        System.out.println(studentBean.getStatus(userId));
        HttpSession sessie = request.getSession();   
        studentBean.setStatus(userId, 2);
        System.out.println(studentBean.getStatus(userId));
        List<UrsStudentrelatie> l = studentBean.getRelaties(userId);
        System.out.println("Relaties voor:");
        for(UrsStudentrelatie sr : l){
            System.out.println(sr.getUrsStudentrelatiePK().getCollega() + " : " + ((sr.getRelatie() == 2) ? "Niet" : "Wel"));
        }
        
        
        /*Vanaf hier stuk nieuwe code matthias*/
        
        List<UrsGebruiker> studenten = commonBean.getStudentenLijst(request.getUserPrincipal().getName());
        sessie.setAttribute("studenten", studenten);
        String stage = request.getParameter("stage");
        System.out.println(stage);
        
        if("voegtoe".equals(stage)){
            System.out.println(request.getParameter("SelectedStudent"));
            int collegaId = commonBean.getUserId(request.getParameter("SelectedStudent"));
            studentBean.setRelatie(userId,collegaId, 1);
            System.out.println("relatie toegevoegd V");
        }
        if("verwijder".equals(stage)){
            System.out.println(request.getParameter("SelectedStudent"));
            int collegaId = commonBean.getUserId(request.getParameter("SelectedStudent"));
            studentBean.setRelatie(userId,collegaId, 2);
            System.out.println("relatie toegevoegd X");
        }
        
        /*Tot hier stuk nieuwe code matthias*/
        
        //studentBean.setRelatie(0, 1, 2); //Pas aan
        //studentBean.setRelatie(0, 2, 1); // Pas aan
        //studentBean.setRelatie(0, 6, 1); // Creeer
        //studentBean.setRelatie(0, 14, 0); // delete
        System.out.println("Relaties Na:");
        l = studentBean.getRelaties(userId);
        sessie.setAttribute("studentenRelaties", l);
        for(UrsStudentrelatie sr : l){
            System.out.println(sr.getUrsStudentrelatiePK().getCollega() + " : " + ((sr.getRelatie() == 2) ? "Niet" : "Wel"));
        }
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
