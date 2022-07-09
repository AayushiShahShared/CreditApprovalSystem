/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mymail;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.CompanyDao;
import com.credapp.entities.Application;
import com.credapp.helper.ConnectionProvider;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.TextEncryptor;

/**
 *
 * @author Aayushi
 */
public class demoS extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("Servlet demoS"); 

//out.println(System.getenv("JASYPT_ENCRYPTOR_PASSWORD"));
//out.println(System.getenv("JASYPT_ENCRYPTOR_ADMIN"));
//            
//            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
//// encryptor.setPassword("credapp");
// encryptor.setPassword(System.getenv("JASYPT_ENCRYPTOR_PASSWORD"));
//     Properties properties = new EncryptableProperties(encryptor);   
//            FileInputStream fis = new FileInputStream("D:\\Libraries\\Documents\\internship\\project\\24 may credapp\\CredApp\\src\\main\\resources\\database.properties");
//
//     properties.load(fis);
//     out.println(properties.getProperty("user"));
//     out.println(properties.getProperty("password"));
//       
//             Class.forName("com.mysql.cj.jdbc.Driver");
//
//     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", properties);
//
//     out.println(con);
////            String secretKey =System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
////     out.println(secretKey);
//
//     out.println("okay");
            
            
            
            out.println(ConnectionProvider.getConnection());
            Connection con = ConnectionProvider.getConnection();
            ApplicationDao adao =  new ApplicationDao(con);
out.println(adao.getAllApplicationsCount());
            CompanyDao cdao = new CompanyDao(con);
            out.println(cdao.getCompaniesCount());
//                      FileInputStream fis = new FileInputStream("D:\\Libraries\\Documents\\internship\\project\\24 may credapp\\CredApp\\src\\main\\resources\\database.properties");
//
//            out.println(fis);
//            Properties p = new Properties();
//            out.println(p);
//            p.load(fis);
//            out.println(p);
//         
            
//            ClassLoader classLoader = demoS.class.getClassLoader();
// File file = new File(classLoader.getResource("database.properties").getFile());
// out.println(file);
//// InputStream inputStream = new FileInputStream(file);
// FileInputStream fileInputStream = new FileInputStream(file);
// out.println("hhhh");
//// out.println(inputStream);
// out.println("hhhh");
// out.println(fileInputStream);
// out.println("hhhh");
            
 
 
//             out.println(demoS.class.getClassLoader().getResourceAsStream("database.properties"));

 
//            FileInputStream f = (FileInputStream)demoS.class.getClassLoader().getResourceAsStream("database.properties");
//            out.println(demoS.class.getClassLoader().getResourceAsStream("database.properties"));
            
//            ConnectionProvider connectionProvider = new ConnectionProvider();
//            Properties properties = new Properties();
//            out.println(properties);
//            out.println();
//            
//            FileInputStream fis  = (FileInputStream) demoS.class.getClassLoader().getResourceAsStream("database.properties");
//            
//            out.println("okay");
//            out.println();
//            
//            
//            
//            
//            
//            out.println(connectionProvider.getDataFromProperties("database.properties"));
//            
//           FileInputStream fiss = (FileInputStream) demoS.class.getClassLoader().getResourceAsStream("database.properties");
//            out.println();
//
//           out.println(fiss);             
//                       out.println();
//
//            Properties pp=connectionProvider.getDataFromProperties("database.properties");
//            out.println(pp);
////            out.println(p.getProperty("user"));
//////out.println(p.getProperty("password"));
////
//////            Connection con = ConnectionProvider.getConnection();
//////            out.println(con);
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
            Logger.getLogger(demoS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(demoS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(demoS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(demoS.class.getName()).log(Level.SEVERE, null, ex);
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
