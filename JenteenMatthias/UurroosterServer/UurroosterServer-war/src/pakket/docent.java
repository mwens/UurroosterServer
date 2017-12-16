/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import beans.commonBeanLocal;
import beans.docentBeanLocal;
import beans.studentBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class docent extends HttpServlet {
    @EJB
    private docentBeanLocal docentBean;
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
        List<UrsKlas> klassen = docentBean.getKlasLijst();
        sessie.setAttribute("klassen", klassen);
        sessie.setAttribute("docentnaam",request.getUserPrincipal().getName());
        String stage = request.getParameter("stage");
        if(stage == null)
            gotoPage("/docent/docent.jsp",request, response);
        else switch (stage) {
            case "eindeKeuzes":
                docentBean.eindeKeuzes();
                gotoPage("/docent/docent.jsp",request, response);
                break;
            case "verwijderen":
                docentBean.removeKlas(Integer.parseInt(request.getParameter("verwijderKlas")));
                klassen = docentBean.getKlasLijst();
                sessie.setAttribute("klassen", klassen);
                gotoPage("/docent/docent.jsp",request, response);
                break;
            case "voegGroepToe":
                docentBean.addKlas(request.getParameter("nieuweGroepNaam"));
                klassen = docentBean.getKlasLijst();
                sessie.setAttribute("klassen", klassen);
                gotoPage("/docent/docent.jsp",request, response);
                break;
            case "voegtoeStudent":
                int voegtoeStudent = commonBean.getUserId(request.getParameter("SelectedStudent"));
                docentBean.setStudentKlas(voegtoeStudent, Integer.parseInt((String)sessie.getAttribute("klasnummer")));
                sessie.setAttribute("overige", docentBean.getKlaslozeStudenten());                
                sessie.setAttribute("klas", docentBean.getStudentenInKlas(Integer.parseInt((String)sessie.getAttribute("klasnummer"))));
                gotoPage("/docent/groepen.jsp",request, response);
                break;
            case "verwijderenStudent":
                docentBean.setStudentKlas(Integer.parseInt(request.getParameter("verwijderStudent")), -1);
                sessie.setAttribute("overige", docentBean.getKlaslozeStudenten());
                sessie.setAttribute("klas", docentBean.getStudentenInKlas(Integer.parseInt((String)sessie.getAttribute("klasnummer"))));
                gotoPage("/docent/groepen.jsp",request, response);
                break;
            case "edit":
                sessie.setAttribute("overige", docentBean.getKlaslozeStudenten());
                sessie.setAttribute("klas", docentBean.getStudentenInKlas(Integer.parseInt(request.getParameter("editKlas"))));
                sessie.setAttribute("klasnummer", request.getParameter("editKlas"));
                sessie.setAttribute("klasnaam", docentBean.getKlas(Integer.parseInt((String)sessie.getAttribute("klasnummer"))).getNaam());
                gotoPage("/docent/groepen.jsp",request, response);
                break; 
            case "bevestigen":
                gotoPage("/docent/docent.jsp",request, response);
                break; 
            default:
                gotoPage("/docent/docent.jsp",request, response);
                break;
        }
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
