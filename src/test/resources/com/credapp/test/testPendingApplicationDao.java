
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.test;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.CompanyDao;
import com.credapp.dao.PendingApplicationDao;
import com.credapp.dao.ReviewApplicationDao;
import com.credapp.entities.Application;
import com.credapp.helper.ConnectionProvider;
import static com.credapp.test.TestApplicationDao.aid;
import static com.credapp.test.TestCompanyDao.con;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Aayushi
 */
public class TestPendingApplicationDao {
    static Connection con = null;
static Application applicationInstance = new Application();

    static PendingApplicationDao pdao = null;
    static ApplicationDao adao = null;
    static ArrayList<Application> pendingList = new ArrayList<>();
    static ArrayList<Application> applicationList = new ArrayList<>();
    static int aid;
//            static Logger LOG =null;
    static final Logger LOG = Logger.getLogger(TestPendingApplicationDao.class.getName());

    @BeforeClass
    public static void setUp() {

        con = ConnectionProvider.getConnection();
//        cdao = new CompanyDao(con);
  pdao= new PendingApplicationDao(con);
  adao= new ApplicationDao(con);

  applicationList=adao.getAllApplicationsFromRange(0, 1);
  for(Application applicationIns : applicationList){
      aid = applicationIns.getId();
  }
        System.out.println("before class");
        System.out.println("aid"+aid);

                   //        set the values to prepared statement
applicationInstance.setId(aid);
       applicationInstance.setCompanyName("NewComp");
applicationInstance.setLoanAmount(10000);
applicationInstance.setPeriod(12);
applicationInstance.setDiscount(8);
applicationInstance.setCibil(100);
applicationInstance.setRevenue(1000000);
applicationInstance.setBankAcc("stdgyfh8ju");
applicationInstance.setEmpExpense(20000);
applicationInstance.setTotalAmt(10034.9f);
applicationInstance.setInterest(129.4f);
applicationInstance.setStatus("Pending");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setReason("Discount is not right.");


        System.out.println("running pending test casses");

    }

    
    @Test
    public void testSubmitPendingForm(){
        
                   Application applicationInstance = new Application();
                   Application application = new Application();

                   //        set the values to prepared statement
applicationInstance.setId(aid);
       applicationInstance.setCompanyName("NewComp");
applicationInstance.setLoanAmount(10000);
applicationInstance.setPeriod(12);
applicationInstance.setDiscount(8);
applicationInstance.setCibil(100);
applicationInstance.setRevenue(1000000);
applicationInstance.setBankAcc("stdgyfh8ju");
applicationInstance.setEmpExpense(20000);
applicationInstance.setTotalAmt(10034.9f);
applicationInstance.setInterest(129.4f);
applicationInstance.setStatus("Pending");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setReason("Discount is not right.");
        
        System.out.println("submit pending form test case");

boolean result=pdao.submitPendingForm(applicationInstance);
Assert.assertTrue("submit pending form test case passed", result);
        
        application = pdao.getPendingApplicationById(aid);
        Assert.assertEquals(applicationInstance.getStatus(), application.getStatus());
        Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
        Assert.assertEquals(applicationInstance.getId(), application.getId());

        System.out.println("submit pending form test case passed");

        pdao.deletePendingApplication(aid);
    }
  
    
    @Test
    public void testGetPendingApplicationsCount() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting pending applications count");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM pendingapplication");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            actualCount = pdao.getPendingApplicationsCount();
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting pending applications count test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    @Test
    public void testGetAllPendingApplicationsFromRange(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Getting applications from range ");
        
boolean result=pdao.submitPendingForm(applicationInstance);
Assert.assertTrue("submit pending form test case passed", result);
        
    
        int count;
        try {
            count = pdao.getPendingApplicationsCount();
    applicationList=pdao.getAllPendingApplicationsFromRange(0, count);
Assert.assertEquals(count, applicationList.size());
for(Application applicationIns: applicationList){
if(applicationIns.getCompanyName().equals("NewComp")){
application = applicationIns;
}
}
            System.out.println(application.getApplicationDate());
            System.out.println(application.getCompanyName());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
            System.out.println("Getting applications from range test case passed");
pdao.deletePendingApplication(aid);
        } catch (SQLException ex) {
            Logger.getLogger(TestPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    
      @Test
    public void testGetAllPendingApplications() {
        ResultSet rs = null;
        int count = 0;
                    System.out.println("Getting all pending applications test case ");

        try {
            
            boolean result=pdao.submitPendingForm(applicationInstance);
            Assert.assertTrue("submit pending application", result);

            rs=pdao.getAllPendingApplications();

            Assert.assertNotNull(rs);

            while (rs.next()) {
                count++;

            }
            Assert.assertEquals(count, pdao.getPendingApplicationsCount());
            Application application = new Application();
            application= pdao.getPendingApplicationById(applicationInstance.getId());
            
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
     Assert.assertEquals(applicationInstance.getId(), application.getId());
            System.out.println("Getting all pending applications test case passed");
            pdao.deletePendingApplication(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
     @Test
    public void testGetAllPendingApplicationsByName() {
        ResultSet rs = null;
        int expectedCount = 0;
        int actualCount = 0;
                    System.out.println("Getting all pending applications by company name test case ");

        try {
            
            boolean result=pdao.submitPendingForm(applicationInstance);
            Assert.assertTrue("submit pending application", result);

            rs=pdao.getAllPendingApplicationsByName(applicationInstance.getCompanyName());

            Assert.assertNotNull(rs);

            while (rs.next()) {
//count++;           
actualCount++;
            }
        
            rs = pdao.getAllPendingApplications();
            Assert.assertNotNull(rs);
 while (rs.next()) {
if(rs.getString("appCompanyName").equals(applicationInstance.getCompanyName())){
expectedCount++;

}     
            }
            System.out.println(expectedCount);
            System.out.println(actualCount);
 Assert.assertEquals(expectedCount, actualCount);
            Application application = new Application();
            application= pdao.getPendingApplicationById(applicationInstance.getId());
            
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
     Assert.assertEquals(applicationInstance.getId(), application.getId());
            System.out.println("Getting all pending applications by company name test case passed");
            pdao.deletePendingApplication(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Test
    public void testGetPendingApplicationById() {
                    System.out.println("Getting pending application by id test case ");

                    boolean result=pdao.submitPendingForm(applicationInstance);
                    Assert.assertTrue("submit pending application", result);
                    Application application = new Application();
                    application=pdao.getPendingApplicationById(applicationInstance.getId());
                    Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
                    Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
                    Assert.assertEquals(applicationInstance.getId(), application.getId());
                    System.out.println("Getting pending application by id test case passed");
                    pdao.deletePendingApplication(applicationInstance.getId());
    }
    
    
     @Test
    public void testDeletePendingApplication() {
        
        
        try {
            System.out.println("delete pending application ");

            boolean result = pdao.submitPendingForm(applicationInstance);
            Assert.assertTrue("delete pending application test case passed", result);
            System.out.println("application submitted");

            Application application = null;
            application = pdao.getPendingApplicationById(applicationInstance.getId());
            System.out.println(application);
            System.out.println(application.getId());
            Assert.assertEquals(applicationInstance.getId(),application.getId());
            Assert.assertNotNull(application);
            Assert.assertNotNull(application.getId());
      
            result=pdao.deletePendingApplication(applicationInstance.getId());
            Assert.assertTrue("delete pending application test case passed", result);
            
            application = pdao.getPendingApplicationById(applicationInstance.getId());
//            Assert.assertNull(application);
            Assert.assertEquals(0,application.getId());
            Assert.assertNull(application.getCompanyName());
      
            
            System.out.println("delete pending application test case passed");
        } catch (Exception e) {
        }
        
    }

    
    @AfterClass
    public static void tearDown() {
        try {
            con.close();
            System.out.println("connection closed");
            System.out.println("After class");
        } catch (SQLException ex) {
//                    Logger.getLogger(TestApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
