/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.test.applicationdao;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.ReviewApplicationDao;
import com.credapp.entities.Application;
import com.credapp.helper.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.BeforeEach;
import org.junit.Test;

/**
 *
 * @author Aayushi
 */
public class testApplicationDao {
    
   static Connection con = null;
static Application applicationInstance = new Application();
    
    static ApplicationDao adao = null;
    static ReviewApplicationDao rdao = null;
           static ArrayList<Application> applicationList = new ArrayList<>();
static int aid;
//            static Logger LOG =null;
static final Logger LOG = Logger.getLogger(testApplicationDao.class.getName());
 
    /**
     *
     */
//            
//
//    @BeforeClass
//    public static void beforeClass(){
//
//  con = ConnectionProvider.getConnection();
//  adao = new ApplicationDao(con);
//  rdao= new ReviewApplicationDao(con);
//
//         }
    
  
    @BeforeClass
    public static void setUp(){
        
  con = ConnectionProvider.getConnection();
  adao = new ApplicationDao(con);
  rdao= new ReviewApplicationDao(con);

  
 try {
            applicationList=rdao.getReviewApplicationsFromRange(0, 1);
            for(Application applicationIns : applicationList){
      applicationIns.setId(applicationIns.getId());
                aid = applicationIns.getId();
            }
        } catch (SQLException ex) {
            Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("before class");
        System.out.println(aid);



applicationInstance.setId(aid);
//applicationInstance.setId(1000);
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
applicationInstance.setStatus("Approved");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setComment("Approved using test case");
applicationInstance.setReason("Discount is not right.");

    }
    
    @Test
    @Transactional
    public void testSubmitApprovedForm(){

                   Application applicationInstance = new Application();

applicationInstance.setId(aid);
//applicationInstance.setId(1000);
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
applicationInstance.setStatus("Approved");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setComment("Approved using test case");
applicationInstance.setReason("Discount is not right.");

//                    boolean result=adao.submitApprovedForm(applicationInstance);
//        assertTrue(adao.submitApprovedForm(applicationInstance));
//assertEquals(true, result);
                try {
                                        boolean result=adao.submitApprovedForm(applicationInstance);
                    
//                    assertEquals(true, result);
                    assertTrue("Submit in approved form passed", result);
                                        LOG.info("submitApprovedform test case passed");

                    adao.deleteApplicationById(applicationInstance.getId());
                } catch (SQLException ex) {
                    LOG.info("submitApprovedform test case failed");
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
       
    }

    
    
    @Test
    public void testGetApprovedApplicationsCount() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting applications with approved status");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM application where appStatus=\"Approved\"");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            actualCount = adao.getApprovedApplicationsCount();
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting applications count with approved status test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(testCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGetRejectedApplicationsCount() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting applications with rejected status");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM application where appStatus=\"Rejected\"");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            
            actualCount = adao.getRejectedApplicationsCount();
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting applications count with rejected status test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(testCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    @Test
    public void testGetAllApplicationsCount() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting all applicatons count test case");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM application");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            
            actualCount = adao.getAllApplicationsCount();
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting all applications count test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(testCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGetApplicationsCount() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting all applicatons count by company name test case");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM application WHERE appCompanyName=\"Wipro\"");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            
            actualCount = adao.getApplicationsCount("Wipro");
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting all applications count by company name test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(testCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
            @Test
    @Transactional
            public void testSubmitPendingForm(){
    
        Application applicationInstance = new Application();

        

        
       applicationInstance.setCompanyName("NewComp");
applicationInstance.setLoanAmount(10000);
applicationInstance.setPeriod(12);
applicationInstance.setDiscount(8);
applicationInstance.setCibil(100);
applicationInstance.setBankAcc("stdgyfh8ju");
applicationInstance.setRevenue(1000000);
applicationInstance.setEmpExpense(20000);
applicationInstance.setStatus("Pending");
applicationInstance.setReason("discount is the reason");


        try {
            boolean result= adao.submitPendingForm(applicationInstance);
            System.out.println(result);
             assertTrue("Submit in application table with pending status passed", result);
                                        LOG.info("submitPendingForm test case passed");

                   applicationInstance=adao.getApplicationByDetails(applicationInstance.getCompanyName(), applicationInstance.getLoanAmount(), applicationInstance.getPeriod(), applicationInstance.getDiscount(), applicationInstance.getCibil(), applicationInstance.getBankAcc(), applicationInstance.getRevenue(), applicationInstance.getEmpExpense());
//                   System.out.println(applicationInstance.getId()); 
                   adao.deleteApplicationById(applicationInstance.getId());
        } catch (Exception e) {
        
        }

    }
    
    @Test
    public void testSubmitForm(){
             Application applicationInstance = new Application();
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
applicationInstance.setStatus("Approved");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setComment("Approved using test case");
applicationInstance.setReason("Discount is not right.");

   try {
                                        boolean result=adao.submitForm(applicationInstance);
                    
//                    assertEquals(true, result);
                    assertTrue("Submit in application table passed", result);
                                        LOG.info("submitform test case passed");

                    adao.deleteApplicationById(applicationInstance.getId());
                } catch (SQLException ex) {
                    LOG.info("submitform test case failed");
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
       
             
    }
    
    
    @Test
    public void testSubmitRejectedFormInApplication(){
             Application applicationInstance = new Application();
             Application application = new Application();

             applicationInstance.setCompanyName("NewComp");
applicationInstance.setLoanAmount(10000);
applicationInstance.setPeriod(12);
applicationInstance.setDiscount(8);
applicationInstance.setCibil(100);
applicationInstance.setRevenue(1000000);
applicationInstance.setBankAcc("stdgyfh8ju");
applicationInstance.setEmpExpense(20000);
applicationInstance.setStatus("Rejected");
applicationInstance.setReason("Discount is not right. cibil is also less.");

   try {
                                        boolean result=adao.submitRejectedFormInApplication(applicationInstance);
                    
//                    assertEquals(true, result);
                    assertTrue("Submit in application table with rejected status directly ", result);
//                                        LOG.info("submitform test case passed");
  application=adao.getApplicationByDetails(applicationInstance.getCompanyName(), applicationInstance.getLoanAmount(), applicationInstance.getPeriod(), applicationInstance.getDiscount(), applicationInstance.getCibil(), applicationInstance.getBankAcc(), applicationInstance.getRevenue(), applicationInstance.getEmpExpense());
                   System.out.println(applicationInstance.getReason()); 
                   System.out.println(application.getReason()); 
                 Assert.assertEquals(applicationInstance.getReason(),application.getReason());
                 System.out.println("Submit in application table with rejected status directly passed");
                 adao.deleteApplicationById(application.getId());
                } catch (SQLException ex) {
                    LOG.info("submitRejectedformInApplication test case failed");
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
       
             
    }
    
    
    
    @Test
    public void testSubmitApprovedFormInApplication(){
             Application applicationInstance = new Application();
             Application application = new Application();
//
             applicationInstance.setCompanyName("NewComp");
applicationInstance.setLoanAmount(10000);
applicationInstance.setPeriod(12);
applicationInstance.setDiscount(8);
applicationInstance.setCibil(100);
applicationInstance.setRevenue(1000000);
applicationInstance.setBankAcc("stdgyfh8ju");
applicationInstance.setEmpExpense(20000);
applicationInstance.setTotalAmt(10456.67f);
applicationInstance.setInterest(123.56f);
applicationInstance.setStatus("Approved");
applicationInstance.setReason("");

   try {
                                        boolean result=adao.submitApprovedFormInApplication(applicationInstance);
                    
//                    assertEquals(true, result);
                    assertTrue("Submit in application table with approved status directly ", result);
//                                        LOG.info("submitform test case passed");
  application=adao.getApplicationByDetails(applicationInstance.getCompanyName(), applicationInstance.getLoanAmount(), applicationInstance.getPeriod(), applicationInstance.getDiscount(), applicationInstance.getCibil(), applicationInstance.getBankAcc(), applicationInstance.getRevenue(), applicationInstance.getEmpExpense());
                   System.out.println(applicationInstance.getReason()); 
                   System.out.println(application.getReason()); 
                 Assert.assertEquals(applicationInstance.getReason(),application.getReason());
                 System.out.println("Submit in application table with approved status directly passed");
                 adao.deleteApplicationById(application.getId());
                } catch (SQLException ex) {
                    LOG.info("submitApprovedformInApplication test case failed");
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
       
      
    }
    
    @Test
    public void testGetApplicationById(){
 
             Application applicationInstance = new Application();
             Application application = new Application();
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
applicationInstance.setStatus("Approved");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setComment("Approved using test case");
applicationInstance.setReason("Discount is not right.");
        try {
                                        boolean result=adao.submitForm(applicationInstance);

                                                                        assertTrue("Submit in application table with rejected status directly ", result);

                                                                          application=adao.getApplicationById(aid);
Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
Assert.assertEquals(applicationInstance.getComment(), application.getComment());
            System.out.println("get application by id test case passed");
   adao.deleteApplicationById(applicationInstance.getId());
        } catch (Exception e) {
        }
        
    }
    
    
    
    @Test
    public void testGetApplications(){
      Application applicationInstance = new Application();
//      Application application = new Application();

applicationInstance.setId(aid);
//applicationInstance.setId(1000);
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
applicationInstance.setStatus("Approved");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setComment("Approved using test case");
applicationInstance.setReason("Discount is not right.");

        try {
                                        boolean result=adao.submitApprovedForm(applicationInstance);
                    
//                    assertEquals(true, result);
                    assertTrue("Submit in approved form passed", result);
//                                        LOG.info("submitApprovedform test case passed");

ResultSet rs=adao.getApplications(applicationInstance.getCompanyName());
rs.next();
Assert.assertEquals("Getting application of particular company", applicationInstance.getReason(), rs.getString("appReason"));
adao.deleteApplicationById(applicationInstance.getId());
                } catch (SQLException ex) {
                    LOG.info("submitApprovedform test case failed");
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
       
    }
    
    @Test
    public void testGetAllApplications(){
      Application applicationInstance = new Application();
      ResultSet rs =null;
      int count=0;
      Application application = new Application();
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
applicationInstance.setStatus("Approved");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setComment("Approved using test case");
applicationInstance.setReason("Discount is not right.");

        try {
                                              boolean result=adao.submitForm(applicationInstance);

            
            rs=adao.getAllApplications();
                    while (rs.next()) {
                count++;
                if(rs.getInt("appId") == aid){
                application.setId(rs.getInt("appId"));
application.setReason(rs.getString("appReason"));
application.setApplicationDate(rs.getTimestamp("appDate"));
                }    
                }
            
                    Assert.assertEquals(adao.getAllApplicationsCount(), count);
                    Assert.assertEquals(applicationInstance.getId(), application.getId());
                    Assert.assertEquals(applicationInstance.getReason(), application.getReason());
                    Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
            System.out.println("get all applications passed");
//                                        LOG.info("submitApprovedform test case passed");


adao.deleteApplicationById(applicationInstance.getId());
                } catch (SQLException ex) {
                    LOG.info("submitApprovedform test case failed");
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
       
    }
    
    
    
     @Test
    public void testGetAllApplicationsFromRange(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Getting applications from range ");
boolean result=        adao.submitForm(applicationInstance);
Assert.assertTrue("submit application form test case passed", result);
        
    
        int count;
        try {
            applicationList = adao.getAllApplicationsFromRange(0, 3);
            System.out.println(applicationList.size());
Assert.assertEquals(3, applicationList.size());
count = adao.getAllApplicationsCount();
applicationList = adao.getAllApplicationsFromRange(0, count);
            System.out.println(count);
            System.out.println(applicationList.size());
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
adao.deleteApplicationById(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(testPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
     @Test
    public void testGetAllApplicationsFromRangeByCompany(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Getting applications from range by company name ");
        
boolean result=adao.submitForm(applicationInstance);
Assert.assertTrue("submit application form test case passed", result);
        
    
        int count;
        try {
            count = adao.getApplicationsCount(applicationInstance.getCompanyName());
    applicationList=adao.getAllApplicationsFromRange(applicationInstance.getCompanyName(), 0, count);
Assert.assertEquals(count, applicationList.size());
for(Application applicationIns: applicationList){
application = applicationIns;
}
            System.out.println(application.getApplicationDate());
            System.out.println(application.getCompanyName());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
            System.out.println("Getting applications from range by company name test case passed");
adao.deleteApplicationById(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(testPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     @Test
    public void testGetApplicationByDetails(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Getting applications by details  ");
        
boolean result=adao.submitForm(applicationInstance);
Assert.assertTrue("submit application form test case passed", result);
        
    
        int count;
        try {

       application = adao.getApplicationByDetails(applicationInstance.getCompanyName(), applicationInstance.getLoanAmount(), applicationInstance.getPeriod(), applicationInstance.getDiscount(), 
               applicationInstance.getCibil(), applicationInstance.getBankAcc(),applicationInstance.getRevenue(), applicationInstance.getEmpExpense());
  
            System.out.println(application.getApplicationDate());
            System.out.println(application.getCompanyName());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
            Assert.assertEquals(applicationInstance.getId(), application.getId());
            System.out.println("Getting applications by details test case passed");
adao.deleteApplicationById(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(testPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     @Test
    public void testDeleteApplicationById(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Delete applications ");
        
boolean result=adao.submitForm(applicationInstance);
Assert.assertTrue("submit application form test case passed", result);
        
    
        int count;
        try {
            application = adao.getApplicationById(applicationInstance.getId());
            System.out.println(application);
            System.out.println(application.getId());
            System.out.println(application.getCompanyName());
            Assert.assertEquals(applicationInstance.getId(),application.getId());
            Assert.assertNotNull(application.getCompanyName());
      
            result =adao.deleteApplicationById(applicationInstance.getId());
            Assert.assertTrue("delete application test case passed", result);
            
            application=adao.getApplicationById(applicationInstance.getId());
            System.out.println(application);
            System.out.println(application.getId());
            System.out.println(application.getCompanyName());
            Assert.assertEquals(0,application.getId());
            Assert.assertNull(application.getCompanyName());
            
            
            Assert.assertNotEquals(applicationInstance.getId(), application.getId());
      
            
            System.out.println("delete application test case passed");
    
            
        } catch (SQLException ex) {
            Logger.getLogger(testPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     *
     */
    @AfterClass
public static void tearDown(){
                try {
                    con.close();
                } catch (SQLException ex) {
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
}
}
