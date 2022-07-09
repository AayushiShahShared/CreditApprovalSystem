package com.credapp.servlets;

import org.apache.log4j.Logger;
import com.credapp.conf.loadPropertyFile;
import com.credapp.dao.ApplicationDao;
import com.credapp.dao.CompanyDao;
import com.credapp.dao.PendingApplicationDao;
import com.credapp.entities.Application;
import com.credapp.entities.Company;
import com.credapp.entities.MailMessage;
import com.credapp.helper.ConnectionProvider;
import com.credapp.services.MailService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
public class ApplicationFormServlet extends HttpServlet {

        static final Logger LOG = Logger.getLogger(ApplicationFormServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            LOG.debug("Application servlet");
// fetching the fields from application form
            int loanAmount = Integer.parseInt(request.getParameter("loanamount"));
            int period = Integer.parseInt(request.getParameter("period"));
            int discount = Integer.parseInt(request.getParameter("discount"));
            int cibil = Integer.parseInt(request.getParameter("cibil"));
            String bankAcc = request.getParameter("bank_acc_no");
            int revenue = Integer.parseInt(request.getParameter("total_revenue_generation"));
            int empExpense = Integer.parseInt(request.getParameter("total_employee_expense"));
            int rate;

            float interest, totalAmt, profit_gained, min_profit;
            String status;
            StringBuilder reason = new StringBuilder();

            String companyName;
            Application application;

            Application applicationInstance;
            PendingApplicationDao pdao = null;
CompanyDao cdao = null;

            String dbStatus = "";

            String mailStatus = "";
            String cEmailMsg = "";
            String cEmailSubject = "CredApp Application report..";
            String cEmailTo = "";
            MailMessage mailObject = null;

          
            
//   getting the current session
            HttpSession session = request.getSession();

//getting the current company name from current session
            Company company = (Company) session.getAttribute("currentCompany");

//get the company name
            companyName = company.getcName();

//loading properties file
            Properties properties = loadPropertyFile.getDataFromProperties("application_form_fields.properties");
            int cibilScore = Integer.parseInt(properties.getProperty("CIBIL"));
            int min_revenue = Integer.parseInt(properties.getProperty("MIN_REVENUE_GENERATION"));
            int max_discount = Integer.parseInt(properties.getProperty("MAX_DISCOUNT"));
            int profit_percent = Integer.parseInt(properties.getProperty("PROFIT_PERCENT"));
            profit_gained = revenue - empExpense;

//calculating profit
            min_profit = (revenue * profit_percent) / 100;

//calculating max loan amount
            float max_loan = min_profit * 5;
            boolean cibilFlag = (cibil > cibilScore);
            boolean revenueFlag = (revenue >= min_revenue);
            boolean profitFlag = (profit_gained >= min_profit);
            boolean loanAmountFlag = (loanAmount < profit_gained);
            boolean discountFlag = (discount <= max_discount);

            if (!cibilFlag) {
                reason.append(" Your cibil score is less than 100. ");
            }

            if (!revenueFlag) {
                reason.append(" Your organisation total revenue generation does not meet the requirements. ");
            }

            if (!profitFlag) {
                reason.append(" Profit gained does not meet the requirement. ");

            }
            if (!loanAmountFlag) {
                reason.append(" Loan amount asked for is getter than profit gained. ");

            }
            if (!discountFlag) {
                reason.append(" Discount percentage asked for does meet the requirement of minimum discount percentage. ");
            }

            String rejectReason = reason.toString();
//            out.println(rejectReason);
// & (discount<=max_discount)

//   checking to either approve the application form or not
            if ((cibil > cibilScore) & (revenue >= min_revenue) & (profit_gained >= min_profit) & (loanAmount < profit_gained)) {

                rate = Integer.parseInt(properties.getProperty("RATE"));

//   calculating the interest
                interest = (loanAmount * period * rate) / 100;

//   calculating the total amount
                totalAmt = interest + loanAmount;

                if (discountFlag) {
                    status = "Approved";

                } else {

                    status = "Pending";

                }

//     create an application object using approval constructor
                application = new Application(companyName, loanAmount, period, discount, cibil, bankAcc, revenue, empExpense, totalAmt, interest, status, rejectReason);

//                out.println(application.getStatus());
                
//     connection to database
                ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());
                pdao = new PendingApplicationDao(ConnectionProvider.getConnection());

//add form data into database
                if (adao.submitApprovedFormInApplication(application)) {
                    LOG.debug("Application submitted successfully : "+application.getCompanyName()+" "+application.getStatus());

                    dbStatus="done";
                    if (status.equalsIgnoreCase("Pending")) {
//out.println("yess");

                        applicationInstance = adao.getApplicationByDetails(companyName, loanAmount, period, discount, cibil, bankAcc, revenue, empExpense);
                        if (applicationInstance != null) {
                            
//                            out.println(applicationInstance.getId());
                            if (pdao.submitPendingForm(applicationInstance)) {
LOG.debug("Application submitted successfully : "+applicationInstance.getCompanyName()+" "+applicationInstance.getId()+" "+applicationInstance.getStatus());

                                int appid = applicationInstance.getId();
//                                out.println(appid);
                                if (adao.deleteApplicationById(appid)) {
LOG.debug("Application deleted successfully : "+applicationInstance.getCompanyName()+" "+applicationInstance.getId()+" "+applicationInstance.getStatus());


                                    dbStatus = "done";
                                } else {
  LOG.warn("Application not deleted  : "+applicationInstance.getCompanyName()+" "+applicationInstance.getId()+" "+applicationInstance.getStatus());

                                    dbStatus = "error";
                                }
                            } else {
           LOG.warn("Application not submitted  : "+applicationInstance.getCompanyName()+" "+applicationInstance.getId()+" "+applicationInstance.getStatus());

                                dbStatus = "error";
                            }
                        }else{
                              LOG.warn("Error in getting Application   : "+companyName+" "+status);

                        dbStatus="error";
                        }

                    }

                } else {
                               LOG.warn("Error in submitting in Application : "+application.getCompanyName()+" "+application.getStatus());

                    dbStatus = "error";

                }
            } else {

                status = "Rejected";

//   create an application object using rejected constructor
                application = new Application(companyName, loanAmount, period, discount, cibil, bankAcc, revenue, empExpense, status, rejectReason);

                //     connection to database
                ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());
//add form data into database
                if (adao.submitRejectedFormInApplication(application)) {
             LOG.debug("Application submitted successfully : "+application.getCompanyName()+" "+application.getStatus());

                    dbStatus = "done";
                } else {
                                                   LOG.warn("Error in submitting in Application : "+application.getCompanyName()+" "+application.getStatus());

                    dbStatus = "error";
                }

////   if(dbStatus.trim().equals("done")){
////session.setAttribute("currentApplication", application);
//RequestDispatcher rd = request.getRequestDispatcher("MailServlet");
//rd.forward(request, response);
            }
//            out.println(dbStatus);
//            out.println(status);

            
            if (status.trim().equalsIgnoreCase("Approved")) {
                cEmailMsg = " Congratulations ... Your application submitted successfully and also being approved . Total Amount to be paid : " + application.getTotalAmt() + " and interest is  " + application.getInterest();
            } else if (status.trim().equalsIgnoreCase("Pending")) {
                cEmailMsg = "Your application submitted successfully but Your application does not meet certain criteria. Your application was currently pending and will be reviwed by our employee and will take decision. \n Sorry for the trouble... Reason of rejection : " + application.getReason();
            } else {
                cEmailMsg = "Sorry !! Your application submitted successfully but your application does not meet certain criteria thus was rejected. Sorry for the trouble... Reason of rejection : " + application.getReason();

            }
cdao = new CompanyDao(ConnectionProvider.getConnection());
            cEmailTo = cdao.getEmailByName(companyName);

            if (dbStatus.equalsIgnoreCase("done")) {
   
                mailObject = new MailMessage();
                mailObject.setMessage(cEmailMsg);
                mailObject.setSubject(cEmailSubject);
                mailObject.setTo(cEmailTo);

//                               MailService mailService = new MailService();
                if (MailService.sendEmail(mailObject)) {
                        LOG.debug("Mail sent successfully : "+application.getCompanyName()+" "+application.getStatus()+" "+mailObject.getTo());

                    mailStatus = "maildone";
                } else {
                                        LOG.warn("Mail not sent : "+application.getCompanyName()+" "+application.getStatus()+" "+mailObject.getTo());

                    mailStatus = "mailerror";
                }

            }

            if (mailStatus.equalsIgnoreCase("maildone")) {
                out.println(mailStatus);
            } else {
                out.println(dbStatus);
            }
            
            
        } catch (SQLException ex) {
      LOG.fatal("Error in connection or SQLException : "+ex);
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
