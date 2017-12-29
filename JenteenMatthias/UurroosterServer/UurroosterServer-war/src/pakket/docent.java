/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pakket;

import beans.commonBeanLocal;
import beans.docentBeanLocal;
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
        
        String gotoPage;
        request.setAttribute("aantalToegewezenStudenten", (int)((float)docentBean.aantalToegewezenStudenten()*100/(float)docentBean.aantalStudenten()));
        if(commonBean.getUserId(request.getUserPrincipal().getName()) == -1){
            gotoPage("Error.jsp", request, response);
            return;
        }

        String stage = request.getParameter("stage");
        if(stage == null)
            stage = "index";
        switch (stage) {
            case "eindeKeuzes":
            case "verwijderen":
            case "bevestigen":
            case "voegGroepToe":
            case "index":
            default:
                gotoPage = this.docentOverzicht(stage, request, response);
                break;
            case "voegtoeStudent":
            case "verwijderenStudent":
            case "edit":
                gotoPage = this.groepOverzicht(stage, request, response);
                break; 
        }
        this.gotoPage(gotoPage, request, response);
    }
    
    public String docentOverzicht(String stage, HttpServletRequest request, HttpServletResponse response){
        HttpSession sessie = request.getSession(); 
        sessie.setAttribute("alert", 0);
        switch (stage) {
            case "eindeKeuzes":
                docentBean.eindeKeuzes();
                break;
            case "verwijderen":
                docentBean.removeKlas(Integer.parseInt(request.getParameter("verwijderKlas")));
                break;
            case "voegGroepToe":
                docentBean.addKlas((String) request.getParameter("nieuweGroepNaam"));
                break;
            case "bevestigen":
                if(docentBean.bevestigen() == -1)
                    sessie.setAttribute("alert", 1);
                break;
            default:
                break;
        }
        sessie.setAttribute("docentnaam",request.getUserPrincipal().getName());
        docentBean.getKlasLijstMetWarnings();
        sessie.setAttribute("klassen",docentBean.getKlasLijst());
        return "/docent/docent.jsp";
    }
    
    public String groepOverzicht(String stage, HttpServletRequest request, HttpServletResponse response){
        HttpSession sessie = request.getSession(); 
        switch (stage) {
        case "voegtoeStudent":
            docentBean.setStudentKlas(commonBean.getUserId(request.getParameter("SelectedStudent")), (int) sessie.getAttribute("klasnummer") );
            break;
        case "verwijderenStudent":
            docentBean.setStudentKlas(Integer.parseInt(request.getParameter("verwijderStudent")), -1);
            break;
        case "edit":
            sessie.setAttribute("klasnummer", Integer.parseInt(request.getParameter("editKlas")));
            break;             
        }
        sessie.setAttribute("overige", docentBean.getKlaslozeStudentenVoorkeur((int) sessie.getAttribute("klasnummer")));
        System.out.println(docentBean.getKlaslozeStudentenVoorkeur((int) sessie.getAttribute("klasnummer")));
        sessie.setAttribute("klas", docentBean.getStudentenInKlas((int) sessie.getAttribute("klasnummer")));
        sessie.setAttribute("klasnaam", docentBean.getKlas((int) sessie.getAttribute("klasnummer")).getNaam());
        return "/docent/groepen.jsp";
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
