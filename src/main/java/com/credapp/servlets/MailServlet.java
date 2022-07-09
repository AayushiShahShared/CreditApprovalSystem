
package com.credapp.servlets;

import com.credapp.entities.Application;
import com.credapp.entities.Company;
import com.credapp.entities.MailMessage;
import com.credapp.services.MailService;
import com.sun.mail.handlers.message_rfc822;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MailServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            String cEmailMsg;
            String cEmailSubject ="CredApp Application report..";
            String cEmailTo;
            String cEmailFrom = "gajendragoswamics18@acropolis.in";
//            String cEmailFrom = "varshadshah161974@gmail.com";
            
            
            HttpSession session = request.getSession();
            Company company=(Company)session.getAttribute("currentCompany");
            cEmailTo = company.getcEmail();

            
            Application application =  (Application)session.getAttribute("currentApplication");
            String status = application.getStatus();
            if(status.trim().equals("Approved"))
            {
               cEmailMsg=" Congratulations ... Your application submitted successfully and also being approved . Total Amount to be paid : " +application.getTotalAmt()+" and interest is  " +application.getInterest();
              
            }
            else
            {
            cEmailMsg="Sorry !! Your application submitted successfully but Your application was rejected...  ";
            
            }
            
            
     MailMessage mailObject = new MailMessage();
mailObject.setMessage(cEmailMsg);
mailObject.setSubject(cEmailSubject);
     mailObject.setTo(cEmailTo);
//     mailObject.setFrom(cEmailFrom);
     
//          mail code

//fetching the details

//create mail message object
//out.println("msg " + mailObject.getMessage());
//out.println("subject " + mailObject.getSubject());
//out.println("to  " + mailObject.getTo());
//out.println("from " + mailObject.getFrom());

MailService mailService =new MailService();
//boolean mailServletSent =mailService.sendEmail(mailObject);
//out.println("<br><br><br><br>");
//out.println(mailServletSent);
if(mailService.sendEmail(mailObject)){
out.println("done");
}
else
{
    out.println("error from mail");
}



// out.println("<br>");
//out.println("application");
//out.println("<br>");
//
//out.println(application.getCompanyName());
// out.println("<br>");
// out.println(application.getLoanAmount());
// out.println("<br>");
//     out.println(application.getPeriod());
//     out.println("<br>");
//     out.println(application.getRate());
//     out.println("<br>");
//     out.println(application.getCibil());
//     out.println("<br>");
//     out.println(application.getBankAcc());
//     out.println("<br>");
//     out.println(application.getRevenue());
//     out.println("<br>");
//     out.println(application.getEmpExpense());
//     out.println("<br>");
//     out.println(application.getTotalAmt());
//     out.println("<br>");
//     out.println(application.getInterest());
//     out.println("<br>");
//     out.println(application.getStatus());
//     out.println("<br>");

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
