/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.servlets;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.PendingApplicationDao;
import com.credapp.entities.Application;
import com.credapp.entities.Company;
import com.credapp.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aayushi
 */
@MultipartConfig
public class SubmitFormServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

// fetching the fields from application form
            int loanAmount = Integer.parseInt(request.getParameter("loanamount"));
            int period = Integer.parseInt(request.getParameter("period"));
            int rate = Integer.parseInt(request.getParameter("rate"));
            int cibil = Integer.parseInt(request.getParameter("cibil"));
            String bankAcc = request.getParameter("bank_acc_no");
            int revenue = Integer.parseInt(request.getParameter("total_revenue_generation"));
            int empExpense = Integer.parseInt(request.getParameter("total_employee_expense"));
            String status = "Pending";
            String companyName;
            Application application;
            String dbStatus;

//   getting the current session
            HttpSession session = request.getSession();

//getting the current company name from current session
            Company company = (Company) session.getAttribute("currentCompany");

//get the company name
            companyName = company.getcName();

//   out.println(loanAmount);
//   out.println(period);
//   out.println(rate);
//   out.println(cibil);
//   out.println(bankAcc);
//   out.println(revenue);
//   out.println(empExpense);
//   out.println(status);
//   out.println(companyName);
//     create an application object using approval constructor
            application = new Application(companyName, loanAmount, period, rate, cibil, bankAcc, revenue, empExpense, status);

//     connection to database
            PendingApplicationDao pdao = new PendingApplicationDao(ConnectionProvider.getConnection());

            ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());
            Application applicationInstance =null;
            if (adao.submitPendingForm(application)) {
//                out.println("application  done");
                applicationInstance = adao.getApplicationByDetails(companyName, loanAmount, period, rate, cibil, bankAcc, revenue, empExpense);
//                out.println("instance");
                if (applicationInstance != null) {
//                                    out.println("got instance");
//
                    if (pdao.submitPendingForm(applicationInstance)) {
//                        out.println("submit pending done ");
                        int appid = applicationInstance.getId();
                        if (adao.deleteApplicationById(appid)) {
//out.println("delete done");
                            dbStatus = "dbdone";
//            out.println(dbStatus);
//
                        } else {
                            dbStatus = "deleterror";
//            out.println(dbStatus);
//
                        }
//
                    } else {
                        dbStatus = "pendingerror";
//            out.println(dbStatus);
//
                    }
//
                } else {
                    dbStatus = "getApplicationError";
//            out.println(dbStatus);
//
                }

            } else {
                
                dbStatus = "applicationError";
//out.println("applicationError");
            
            }
//add form data into database

            out.println(dbStatus);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubmitFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SubmitFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
