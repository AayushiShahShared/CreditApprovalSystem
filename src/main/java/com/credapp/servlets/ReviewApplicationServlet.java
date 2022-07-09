/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.servlets;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.ReviewApplicationDao;
import com.credapp.entities.Application;
import com.credapp.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author Aayushi
 */
@MultipartConfig
public class ReviewApplicationServlet extends HttpServlet {

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

//            HttpSession session = request.getSession();
//            Application application = (Application)session.getAttribute("currentapplication");
//            String btnValue=request.getParameter("reject-btn");
//        out.println(application.getId());
//        String appid = request.getParameter("appid");
//        out.println(appid);
            out.println("hii");
            out.println(request.getParameter("appid"));
            out.println(request.getParameter("reviewType"));
            out.println(request.getParameter("count"));
            out.println(request.getParameter("cmt"));

            int appId = Integer.parseInt(request.getParameter("appid"));
            String reviewType = request.getParameter("reviewType");
            String commenttext = request.getParameter("cmt");
            out.println(commenttext);

            Application application;
            ReviewApplicationDao rdao = new ReviewApplicationDao(ConnectionProvider.getConnection());
            String dbStatus = "", deleteStatus = "", bankAcc, status, companyName, reason;
            float totalAmt, interest;
            int aid, cibil, loanAmount, period, discount, revenue, empExpense;
            Timestamp appDate;

            application = rdao.getById(appId);

//            status = "Approved";
            aid = application.getId();
            companyName = application.getCompanyName();
            cibil = application.getCibil();
            loanAmount = application.getLoanAmount();
            period = application.getPeriod();
            discount = application.getDiscount();
            bankAcc = application.getBankAcc();
            revenue = application.getRevenue();
            empExpense = application.getEmpExpense();
            totalAmt = application.getTotalAmt();
            interest = application.getInterest();
//            status = application.getStatus();
            appDate = application.getApplicationDate();
            reason = application.getReason();

            application = rdao.getById(aid);
            out.print("<br>");
            out.println(application.getId());
            out.println(application.getCompanyName());
            out.println(application.getLoanAmount());
            out.println(application.getPeriod());
            out.println(application.getDiscount());
            out.println(application.getCibil());
            
            out.println(application.getRevenue());
            out.println(application.getBankAcc());
            out.println(application.getEmpExpense());
            
            out.println(application.getTotalAmt());
            out.println(application.getInterest());

            out.println(application.getStatus());
            out.println(application.getApplicationDate());

            out.println(application.getComment());
            out.println(application.getReason());
            out.print("<br>");

            if (application != null) {
                if (reviewType.equals("approve")) {
                    status = "Approved";
                } else {
                    status = "Rejected";

                }

//create an application object using approval constructor
//                application = new Application(aid, companyName, loanAmount, period, discount, cibil, bankAcc, revenue, empExpense, totalAmt, interest, status, appDate, commenttext, reason);
application.setComment(commenttext);
application.setStatus(status);
                out.print("<br>");
            out.println(application.getId());
            out.println(application.getCompanyName());
            out.println(application.getLoanAmount());
            out.println(application.getPeriod());
            out.println(application.getDiscount());
            out.println(application.getCibil());
            
            out.println(application.getRevenue());
            out.println(application.getBankAcc());
            out.println(application.getEmpExpense());
            
            out.println(application.getTotalAmt());
            out.println(application.getInterest());

            out.println(application.getStatus());
            out.println(application.getApplicationDate());

            out.println(application.getComment());
            out.println(application.getReason());
            out.print("<br>");

//object to add data to application table
                ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());
out.print("dao okay");

                            out.print("<br>");

//                            out.println(adao.submitForm(application));
//                            out.println(adao.submitApprovedForm(application));
                if (adao.submitForm(application)) {
                    dbStatus = "dbdone";

//out.print(dbStatus);
                } else {
                    dbStatus = "error";
//out.print(dbStatus);
                }
                            out.print("<br>");

out.println(dbStatus);
                
                if (dbStatus.equals("dbdone")) {
//                    out.println(application.getId());
                    aid = application.getId();

                    if (rdao.deleteReviewById(aid)) {
                        dbStatus = "deletedone";
                        HttpSession session = request.getSession();
                        session.setAttribute("currentapplication", application);
                        session.setAttribute("dbstatus", dbStatus);
                        response.sendRedirect("analyst_view_application.jsp");
                    } else {
                        dbStatus = "deleteError";

                    }

                    out.println(dbStatus);
                }

                if (dbStatus.equals("deleteError")) {

                    out.print("no application found");
                }

//if (application != null) {
//                if (reviewType.equals("approve")) {
////                out.println("approve");
//                    try {
////                       
////                      
//////                        if (cibil > 100) {
////// calculating the interest
////                        interest = (loanAmount * period * rate) / 100;
//
////   calculating the total amount
////                        totalAmt = interest + loanAmount;
//
////    application approved
//                        status = "Approved";
//
////create an application object using approval constructor
//                        application = new Application(aid, companyName, loanAmount, period, discount, cibil, bankAcc, revenue, empExpense, totalAmt, interest, status, appDate,commenttext,reason);
////                        out.println(application.getId());
////                        out.println(application.getCompanyName());
////                        out.println(application.getRevenue());
////                        out.println(application.getRate());
////                        out.println(application.getLoanAmount());
////                        out.println(application.getPeriod());
////                        out.println(application.getCibil());
////                        out.println(application.getRate());
////                        out.println(application.getBankAcc());
////                        out.println(application.getEmpExpense());
////                        out.println(application.getStatus());
////                        out.println(application.getInterest());
////                        out.println(application.getTotalAmt());
////                        out.println(application.getApplicationDate());
//
////object to add data to application table
//                        ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());
////out.println("ohk");
////boolean submit=adao.submitApprovedForm(application);
////out.println(submit);
//                    
//if (adao.submitApprovedForm(application)) {
//                            dbStatus = "dbdone";
//
////out.print(dbStatus);
//                        } else {
//                            dbStatus = "error";
////out.print(dbStatus);
//                        }
//
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
////                
////                //                            submit reject form
//                    status = "Rejected";
//
////   create an application object using rejected constructor
//                    
//
//                        application = new Application(aid, companyName, loanAmount, period, discount, cibil, bankAcc, revenue, empExpense, totalAmt, interest, status, appDate,commenttext,reason);
//
//
//                    //     connection to database
//                    ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());
//
//                    
//                    
//                    
//                    
////add form data into database
//                    if (adao.submitRejectedForm(application)) {
//                        dbStatus = "dbdone";
//
//                    } else {
//                        dbStatus = "error";
//                    }
//
//                    
//                    
//                    
//                }
            }            
out.println(dbStatus);

//                out.println("reject");
//            }
                HttpSession s = request.getSession();
                s.setAttribute("reload", "yes");

                out.println("<a href='analyst_review_application.jsp' class='btn'>Go back</a>");
                out.println(dbStatus);
                out.println(deleteStatus);
            
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
            Logger.getLogger(ReviewApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReviewApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
