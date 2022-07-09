/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.servlets;

import com.credapp.dao.CompanyDao;
import com.credapp.entities.Application;
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

/**
 *
 * @author Aayushi
 */
@MultipartConfig
public class VerifyServlet extends HttpServlet {

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
boolean verify = false;
Company company = new Company();

String human_check = request.getParameter("human_verify");
//            out.println(human_check);
            
if (        human_check == null ){
    out.println("notchecked");
}
else{
//    out.println("done");
          
HttpSession session = request.getSession();
String otpType = (String)session.getAttribute("type");
String page = (String)session.getAttribute("page");
//out.println(otpType);
//out.println(page);

//String otpCode = (String)session.getAttribute("code");
String code = request.getParameter("verification_code");
//out.println(code);

//       create a CompanyDao object
CompanyDao cdao = new CompanyDao(ConnectionProvider.getConnection());
            Company companyCode= (Company)session.getAttribute("currentcompanycode");
//out.println(companyCode.getCode());
//out.println(companyCode.getcEmail());
company = cdao.getCompanyByEmail(companyCode.getcEmail());
//out.println(company.getcEmail());

if(company == null){
//out.println("company nhi h ");
response.sendRedirect("email.jsp");
}
else{
//out.println("company");
//out.println(company.getcId());
//out.println(company.getcName());
//out.println(company.getcRNo());
//out.println(company.getcCategory());
//out.println(company.getcClass());
//out.println(company.getcAbout());
//out.println(company.getcAge());
//out.println(company.getcSize());
//out.println(company.getcEmail());
//out.println(company.getcPassword());
//out.println(company.getcType());
//out.println(company.getVerify());

if(code.equals(companyCode.getCode()))
{
    verify=true;
    company.setVerify("yes");
//    out.println(company.getVerify());
}
else{
verify=false;
out.println("noVerify");
//    out.println(company.getVerify());
}

if(verify){
if(otpType.equals("pass")){
out.println("otpVerifyPass");
}
if(otpType.equals("notverify")){

                    if (cdao.verifyCompany(company)) {
//registered
out.println("VerificationUpdate");
} else{
out.println("error");
}

}
}
}

//if( !page.equals("register")){
//               } else {
//    company =(Company)session.getAttribute("currentcompany");
//    }
              
//if(company == null){
//response.sendRedirect("email.jsp");
//}
//else{
//            if(code.equals(companyCode.getCode())){
//                 verify =true;
//                company.setVerify("yes");                 
//            }
//            else{
//      verify=false;
//            out.println("noVerify");
//            }
//out.println(verify);
//            if(verify){
//            
//            if(otpType.equals("notverify")){
//            
//             
//                if (cdao.verifyCompany(company)) {
////registered
//out.println("VerificationUpdate");
//} else{
//out.println("error");
//}
//            }
//            if(otpType.equals("pass")){
//            out.println("otpVerifyPass");
//            }
//     
//            session.removeAttribute("page");
//            
//
//            }
//}          

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
    
