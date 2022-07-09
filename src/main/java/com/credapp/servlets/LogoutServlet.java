
package com.credapp.servlets;

import com.credapp.entities.Company;
import com.credapp.entities.Message;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


public class LogoutServlet extends HttpServlet {

                static final Logger LOG = Logger.getLogger(LogoutServlet.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session =request.getSession();

            Company company=(Company)session.getAttribute("currentCompany");
            LOG.debug("trying logout of account : "+company.getcId()+ " " +company.getcName()+ " " + company.getcEmail() );
            out.println("<title>Servlet LogoutServlet</title>");            
          
            session.removeAttribute("currentCompany");
            
            Message msg =new Message("Logout successfully ! Login Again","success","alert-success");
     
            session.setAttribute("msg", msg);
           
             LOG.debug("successfull logout of account : "+company.getcId()+ " " +company.getcName()+ " " + company.getcEmail() );

            response.sendRedirect("login_page.jsp");

                      
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
