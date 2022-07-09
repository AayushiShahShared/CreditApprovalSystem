
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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@MultipartConfig
public class EditServlet extends HttpServlet {

                static final Logger LOG = Logger.getLogger(EditServlet.class);

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
                                    LOG.debug("Edit Company servlet called");

//            fetching all editable fields data
            String cName = request.getParameter("company_name");
            String cEmail = request.getParameter("company_email");
            String cPassword = request.getParameter("company_password");
            String cStatus = request.getParameter("company_status");
            String cCategory = request.getParameter("company_category");
            String cClass = request.getParameter("company_class");
             int cAge = Integer.parseInt(request.getParameter("company_age"));
             int cSize = Integer.parseInt(request.getParameter("company_size"));
             String cOfficeLocs = request.getParameter("company_office_loc");
             String cAbout = request.getParameter("company_about");
            
//             create an http session 
HttpSession session =request.getSession();

//get the current company data from current session 
Company company = (Company) session.getAttribute("currentCompany");



//setting the new data into current company object
company.setcName(cName);
company.setcEmail(cEmail);
company.setcPassword(cPassword);
company.setcStatus(cStatus);
company.setcCategory(cCategory);
company.setcClass(cClass);
company.setcOfficeLocs(cOfficeLocs);
company.setcAge(cAge);
company.setcSize(cSize);
company.setcAbout(cAbout);

// get the company dao object to update company details and pass the database connection object
CompanyDao cdao =new CompanyDao(ConnectionProvider.getConnection());

// updating the company details
if(cdao.updateCompanyDetails(company)){
                                LOG.debug("company details updated successfully : "+ company + " company name : "+company.getcName());

        out.println("done");
        }
else{
                                    LOG.warn("company details not updated : "+ company + " company name : "+company.getcName());

        out.println("error");
        }

//out.println(company);
//out.println("<br>");
//
//out.println(company.getcId());
//out.println("<br>");
// out.println(company.getcName());
//out.println("<br>");
//   out.println(company.getcEmail());
//out.println("<br>");
//   out.println(company.getcPassword());
//out.println("<br>");
//   out.println(company.getcStatus());
//out.println("<br>");
//   out.println(company.getcCategory());
//out.println("<br>");
//   out.println(company.getcClass());
//out.println("<br>");
//   out.println(company.getcAge());
//out.println("<br>");
//   out.println(company.getcSize());
//out.println("<br>");
//   out.println(company.getcOfficeLocs());
//out.println("<br>");
//   out.println(company.getcAbout());
//out.println("<br>");
//out.println("<br>");out.println("<br>");out.println("<br>");

















//out.println(company);
//   out.println(cName);
//   out.println(cEmail);
//   out.println(cPassword);
//   out.println(cStatus);
//   out.println(cCategory);
//   out.println(cClass);
//   out.println(cAge);
//   out.println(cSize);
//   out.println(cOfficeLocs);
//   out.println(cAbout);


//
//   out.println(company.getcName());
//   out.println(company.getcEmail());
//   out.println(company.getcPassword());
//   out.println(company.getcStatus());
//   out.println(company.getcCategory());
//   out.println(company.getClass());
//   out.println(company.getcAge());
//   out.println(company.getcSize());
//   out.println(company.getcOfficeLocs());
//   out.println(company.getcAbout());

////create the company dao object to update 
//CompanyDao cdao =new CompanyDao(ConnectionProvider.getConnection());
//
//// call the update method to update company details
//if(cdao.updateCompanyDetails(company)){
////    updated
//out.println("update");
//}else{
////    not updated
//    out.println("notUpdated");
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
