/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.servlets;

import com.credapp.entities.Company;
import com.credapp.entities.MailMessage;
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

/**
 *
 * @author Aayushi
 */
@MultipartConfig
public class EmailServlet extends HttpServlet {

    static final Logger LOG = Logger.getLogger(EmailServlet.class);

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

            LOG.debug("Email servlet called");

            String checked = request.getParameter("human_verify");
            if (checked == null) {
                out.println("notchecked");
            } else {
                String email = request.getParameter("company_email");

                MailService mailService = new MailService();
                String otp = mailService.getRandom();
                MailMessage message = new MailMessage();
                message.setMessage("Verfication Code " + otp);
                message.setTo(email);
                message.setSubject("Otp");

                Company companyCode = new Company();
                companyCode.setcEmail(email);
                companyCode.setCode(otp);

                if (mailService.sendEmail(message)) {
                    LOG.debug("Mail sent successfully to : " + message.getTo());
                    HttpSession session = request.getSession();
                    String page = (String) session.getAttribute("page");
                    session.setAttribute("type", "notverify");

                    session.removeAttribute("page");
                    session.setAttribute("verification_code", otp);
                    session.setAttribute("currentcompanycode", companyCode);
//           session.setAttribute("type", "otp");
                    out.println("CodeSent");
//                       response.sendRedirect("verify.jsp");
                } else {
                    LOG.warn("Mail not sent to : " + message.getTo());

                    out.println("NotSent");
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
