/*
 * 
 */
package Controleur;

import Actions.SessionAction;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nicolas
 */
public class Control extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher dispatch;

        String action = request.getParameter("action");
        int session_id = Integer.parseInt(request.getParameter("session_id"));

        switch (action) {

            case "session_start":
                (new SessionAction()).start(0, 0);
                dispatch = getServletContext().getRequestDispatcher("/session1.jsp");
                dispatch.forward(request, response);
                break;
            case "session_stop":
                (new SessionAction()).stop(0);
                dispatch = getServletContext().getRequestDispatcher("/session0.jsp");
                dispatch.forward(request, response);
                break;
            case "session_trait": {
                try {
                    (new SessionAction()).postTraitement(session_id);
                } catch (ParseException ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dispatch = getServletContext().getRequestDispatcher("/session.jsp");
            dispatch.forward(request, response);
            break;
        }

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
