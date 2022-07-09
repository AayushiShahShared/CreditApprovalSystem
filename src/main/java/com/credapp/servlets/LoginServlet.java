
package com.credapp.servlets;

import com.credapp.dao.CompanyDao;
import com.credapp.entities.Company;
import com.credapp.entities.Message;
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

@MultipartConfig
public class LoginServlet extends HttpServlet {

    static final Logger LOG = Logger.getLogger(LoginServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
//fetching the email and password from request
String cName=request.getParameter("company_name");
String cPassword=request.getParameter("company_password");
//out.println(cName);
//out.println(cPassword);
LOG.debug("Login form started");

//creating the object of CompanyDao to get the connection of DataBase
CompanyDao cdao =new CompanyDao(ConnectionProvider.getConnection());

//checking the credentials and if credentials are true then getting the company object 
Company company = cdao.getCompanyByNameAndPassword(cName, cPassword);
//out.println(company.getcPassword());

HttpSession session = request.getSession();

if(company==null){
    
//    out.println("Login error .. invalid username or password");
Message msg = new Message("Invalid details ! try with another", "error","alert-danger");
session.setAttribute("msg", msg);
out.println("invalid");

LOG.warn("Login is unsuccessfull"+ msg.toString() + cName);

//response.sendRedirect("login_page.jsp");
}else{
String verify = company.getVerify();
String userType = company.getcType();
String isEnable = company.getIsEnable();
//out.println(verify);
//out.println(userType);
//out.println(isEnable);
if(isEnable.equalsIgnoreCase("true")){
//    out.println(isEnable);
LOG.debug("Account is enabled : " +cName);
    if(verify.equals("yes")){
        LOG.debug("Account is verified : " +cName);

    session.setAttribute("currentCompany", company);
    LOG.debug("Varified and enabled "+company.toString());
    if(userType != null ){
            switch (userType) {
                case "admin":
                    LOG.debug("admin login successfully");
                    out.println("admin");
                    break;
                case "fAnalyst":
                    LOG.debug(userType+" login successfully");
                    
                    out.println("financialAnalyst");
                    break;
                default:
                    LOG.debug(" regular user login successfully");
                    
                    out.println("home");
                    break;
            }
//    if(userType.equals("admin")){
//        out.println("admin");
//    
//    }
//    else if(userType.equals("fAnalyst")){
//    out.println("financialAnalyst");
//    }
//    else{
//out.println("home");
//    }

    }
//    response.sendRedirect("home_page.jsp");
    }
    else {
        LOG.warn("Account is not verified : "+cName);
    session.setAttribute("type", "notverify");
    session.setAttribute("page", "login");
    out.println("notverified");
//    response.sendRedirect(verify);
    }
}
else{
    LOG.warn("Account is disabled : " + cName);
out.println("disable");
}
    
}    
//    if logged in successfully then creating session for company
//HttpSession session = request.getSession();

//    printing the company details after login successfully
//out.println(company.getcId());
//out.println(company.getcName());
//out.println(company.getcRNo());
//out.println(company.getcStatus());
//out.println(company.getcCategory());
//out.println(company.getcClass());
//out.println(company.getcAge());
//out.println(company.getcSize());
//String[] officeLocs = company.getcOfficeLocs().split(" ");
//out.println("locations");
//            for (int i = 0; i < officeLocs.length; i++) {
//                out.println("<br>");
//                out.println(officeLocs[i]);
//            }
//
//            out.println("<br>");
//out.println(company.getcOfficeLocs());
//out.println(company.getcEmail());
//out.println(company.getcAbout());
//out.println(company.getcRegDateTime());


//}


          
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
