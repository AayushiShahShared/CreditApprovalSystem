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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Aayushi
 */
public class DisableCompanyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

            static final Logger LOG = Logger.getLogger(DisableCompanyServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
                        LOG.debug("Disable Company servlet called");

            String companyName=request.getParameter("name");
            out.println(companyName);
            
           String companyEmail=request.getParameter("email");
            out.println(companyEmail);
            
           String isEnable=request.getParameter("isEnable");
            out.println(isEnable);
            String updateEnable="";
            if(isEnable.equalsIgnoreCase("Enable")){
            updateEnable = "true";
            }
            else{
                updateEnable= "false";
            }
            
            String updateCompany= "";
            try{
            CompanyDao cdao= new CompanyDao(ConnectionProvider.getConnection());
            Company company = cdao.getCompanyByEmail(companyEmail);
            if(cdao.updateDisbleCompanyByName(companyName, updateEnable)){
                            LOG.debug("updated company with "+ isEnable + " company name : "+companyName);

            updateCompany="disableDone";
//            out.print(deleteCompany);
            
            }
            else{
                                            LOG.warn("not able to update " +isEnable+ " company name : "+companyName);

                updateCompany="disableError";
            }
            out.println(updateCompany);
  HttpSession session=request.getSession();
            session.setAttribute("currentDisableCompany", company);
                        session.setAttribute("disableStatus", updateCompany);
                        session.setAttribute("isEnable", isEnable);
                     
                        response.sendRedirect("admin_view_disable_company.jsp");
           
            }
            catch(IOException e){
              LOG.warn("Exception occurred at Disable Company servlet : "+ e);
//              LOG.fatal(e.printStackTrace());
            }
            out.println("<a href='admin_disable_users.jsp' class='btn'>Go back</a>");
  
  
  
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
