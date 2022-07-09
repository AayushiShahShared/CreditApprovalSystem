
package com.credapp.servlets;

import com.credapp.entities.Application;
import com.credapp.entities.Company;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AppFormServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("hiiiiiiiiiiii");
   
         
            // fetching the fields from application form
    int loanAmount =Integer.parseInt(request.getParameter("loanamount"));
//    out.println("Application form servlet");
//out.println("hiiiii");
    int period =Integer.parseInt(request.getParameter("period"));
    int rate =Integer.parseInt(request.getParameter("rate"));
     int cibil =Integer.parseInt(request.getParameter("cibil"));  
     String bankAcc  =request.getParameter("bank_acc_no");  
   int revenue =Integer.parseInt(request.getParameter("total_revenue_generation"));  
     int empExpense =Integer.parseInt(request.getParameter("total_employee_expense"));  
float interest;
   float totalAmt;
   String status; 
   String companyName;
      
//out.println("<br>");
//     out.println(loanAmount);
//     out.println("<br>");
//     out.println(period);
//     out.println("<br>");
//     out.println(rate);
//     out.println("<br>");
//     out.println(cibil);
//     out.println("<br>");
//     out.println(bankAcc);
//     out.println("<br>");
//     out.println(revenue);
//     out.println("<br>");
//     out.println(empExpense);
//     out.println("<br>");
//     out.println("Application form servlet");
//     out.println("<br>");
//out.println("hiiiii");
   

//   getting the current session
HttpSession session = request.getSession();

//getting the current company name from current session
Company company = (Company) session.getAttribute("currentCompany");

//get the company name
companyName=company.getcName();


//out.println("<br>");
//out.println(companyName);


//checking to either approve the application form or not
   if(cibil>100)
   {
//   calculating the interest
       interest=(loanAmount*period*rate)/100;
       
//   calculating the total amount
    totalAmt=interest+loanAmount;
    
//    application approved
    status ="Approved";
    
//    string to be returned
    String htmlResponse="<html>";
    htmlResponse+="<h2> Interest is: "+ interest +"<br/>";
    htmlResponse+="<h2>Total Amount to be paid after "+ period +"years "+ +totalAmt+"<br/>";
    htmlResponse+="</html>";
    
  
   out.println(htmlResponse);
 out.println(companyName);
   out.println("<br>");
   out.println(loanAmount);
     out.println("<br>");
     out.println(period);
     out.println("<br>");
     out.println(rate);
     out.println("<br>");
     out.println(cibil);
     out.println("<br>");
     out.println(bankAcc);
     out.println("<br>");
     out.println(revenue);
     out.println("<br>");
     out.println(empExpense);
     out.println("<br>");
     out.println(interest);
     out.println("<br>");
     out.println(totalAmt);
     out.println("<br>");
     out.println(status);
     out.println("<br>");
     
//      create an application object using approval constructor
      Application application = new Application(companyName, loanAmount, period, rate, cibil, bankAcc, revenue, empExpense, totalAmt, interest, status); {
     
     
 out.println(application.getCompanyName());
 out.println("<br>");
 out.println(application.getLoanAmount());
 out.println("<br>");
     out.println(application.getPeriod());
     out.println("<br>");
     out.println(application.getRate());
     out.println("<br>");
     out.println(application.getCibil());
     out.println("<br>");
     out.println(application.getBankAcc());
     out.println("<br>");
     out.println(application.getRevenue());
     out.println("<br>");
     out.println(application.getEmpExpense());
     out.println("<br>");
     out.println(application.getTotalAmt());
     out.println("<br>");
     out.println(application.getInterest());
     out.println("<br>");
     out.println(application.getStatus());
     out.println("<br>");
  
     
   }
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