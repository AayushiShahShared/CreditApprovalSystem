package com.credapp.servlets;

import com.credapp.dao.CompanyDao;
import com.credapp.entities.Company;
import com.credapp.entities.MailMessage;
import com.credapp.helper.ConnectionProvider;
import com.credapp.services.MailService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@MultipartConfig
public class RegisterServlet extends HttpServlet {

        static final Logger LOG = Logger.getLogger(RegisterServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

//            Fetching check fields from register form
            String check = request.getParameter("company_check");
            if (check == null) {
                out.println("notchecked");
            } else {
boolean register = false;
//fetching all fields if checkbox is checked
                String cName = request.getParameter("company_name");
                int cRNo = Integer.parseInt(request.getParameter("company_rno"));
                String cStatus = request.getParameter("company_status");
                String cCategory = request.getParameter("company_category");
                String cClass = request.getParameter("company_class");
                int cAge = Integer.parseInt(request.getParameter("company_age"));
                int cSize = Integer.parseInt(request.getParameter("company_size"));
                String cOfficeLocs = request.getParameter("company_office_loc");
                String cEmail = request.getParameter("company_email");
                String cPassword = request.getParameter("company_password");

                String cAbout = request.getParameter("company_about");
                
               LOG.debug("Register form started");

//            out.println(cName);
//            out.println(cRNo);
//            out.println(cStatus);
//            out.println(cCategory);
//            out.println(cClass);
//            out.println(cAge);
//            out.println(cSize);
//            out.println(cOfficeLocs);
//            out.println(cEmail);
//            out.println(cPassword);
//            out.println(cAbout);

MailService mailService = new MailService();
//
        String code = mailService.getRandom();
                String cEmailTo = cEmail;
                String cEmailMsg = "Registered successfully. Please verify your account using this code : " + code;
                String cEmailSubject = "Verification code";

//            out.println(cEmailTo);
//            out.println(cEmailMsg);
//            out.println(cEmailSubject);
//            out.println(cPassword);
//            out.println(cAbout);

//
////             create Company object and set all the data to that object
                Company companyCode = new Company(cName, cRNo, cStatus, cCategory, cClass, cAge, cSize, cOfficeLocs, cEmail, cPassword, cAbout,code);
                Company company = new Company(cName, cRNo, cStatus, cCategory, cClass, cAge, cSize, cOfficeLocs, cEmail, cPassword, cAbout);
                
MailMessage mailMessage = new MailMessage();
mailMessage.setMessage(cEmailMsg);
mailMessage.setSubject(cEmailSubject);
     mailMessage.setTo(cEmailTo);

     HttpSession session = request.getSession();
                

//       create a CompanyDao object
                CompanyDao cdao = new CompanyDao(ConnectionProvider.getConnection());

                if (cdao.registerCompany(company)) {
                LOG.debug("Registered successfully " + company.getcId());
    
                    
//registered                    
                     boolean sent = mailService.sendEmail(mailMessage);
                if(sent){
                                    LOG.debug("Mail sent successfully " + company.getcId()+ " "+mailMessage.getTo());

                session.setAttribute("currentcompany", company);
                session.setAttribute("currentcompanycode", companyCode);
     session.setAttribute("type", "notverify");
     session.setAttribute("page", "register");
     
                out.println("registerAndCodeSent");
                }
                else{
                                    LOG.warn("Mail not sent  " + company.getcId()+ " "+mailMessage.getTo());

                    out.println("registerAndCodeNotSent");
                }
             } else {
                                    LOG.warn("Company not registered  due to not unique company name or email id : " + company);          
                    out.println("common");
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
