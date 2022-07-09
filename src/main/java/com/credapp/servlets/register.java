
package com.credapp.servlets;

import com.credapp.dao.CompanyDao;
import com.credapp.entities.Company;
import com.credapp.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@MultipartConfig
public class register extends HttpServlet {

        static final Logger LOG = Logger.getLogger(register.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
           
//            Fetching check fields from register form
            
            String check = request.getParameter("company_check");
            if(check == null){
            out.println("notchecked");
            }
            else{
//                     fetching all fields if checkbox is checked
            String cName = request.getParameter("company_name");
            int cRNo = Integer.parseInt(request.getParameter("company_rno"));
            String cStatus=request.getParameter("company_status");
            String cCategory=request.getParameter("company_category");
           String cClass=request.getParameter("company_class");
            int cAge = Integer.parseInt(request.getParameter("company_age"));
            int cSize = Integer.parseInt(request.getParameter("company_size"));
            String cOfficeLocs=request.getParameter("company_office_loc");
            String cEmail=request.getParameter("company_email");
            String cPassword=request.getParameter("company_password");
            String cAbout=request.getParameter("company_about");
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
//            
//             create Company object and set all the data to that object
Company company = new Company(cName,cRNo,cStatus,cCategory,cClass,cAge,cSize,cOfficeLocs,cEmail,cPassword,cAbout);

//            create a CompanyDao object
CompanyDao  cdao= new CompanyDao( ConnectionProvider.getConnection());

if(cdao.registerCompany(company))
{
//registered
    out.println("done");
}     
else{
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
