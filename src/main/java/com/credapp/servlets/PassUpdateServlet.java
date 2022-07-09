/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.servlets;

import com.credapp.dao.CompanyDao;
import com.credapp.entities.Company;
import com.credapp.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Aayushi
 */
@MultipartConfig
public class PassUpdateServlet extends HttpServlet {

            static final Logger LOG = Logger.getLogger(PassUpdateServlet.class);

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
        try (PrintWriter out = response.getWriter()) {
           
                                    LOG.debug("Password update servlet called");

            
            String pass =request.getParameter("company_password");
            String confirmPass= request.getParameter("company_confirm_password");
//            out.println(pass);
//            out.println(confirmPass);
        if(pass.equals(confirmPass)){
        
            HttpSession session = request.getSession();
            Company company=(Company)session.getAttribute("currentcompanycode");
            
//           out.println( company.getcEmail());
          CompanyDao dao = new CompanyDao(ConnectionProvider.getConnection());

          if(dao.updatePassword(company.getcEmail(), pass)){
              LOG.debug("company password updated successfully of account : "+company.getcEmail());
              session.removeAttribute("pass");
          out.println("passUpdate");
          }
          else{
                            LOG.warn("company password not updated of account : "+company.getcEmail());

          out.println("passNotUpdate");
          }
        }
        else{
            LOG.warn("company password not updated due to not entering same password in both the fields  ");
        out.println("notSame");
        }
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
