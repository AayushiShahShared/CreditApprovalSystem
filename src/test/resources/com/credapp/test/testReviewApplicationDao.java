/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.test;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.PendingApplicationDao;
import com.credapp.dao.ReviewApplicationDao;
import com.credapp.entities.Application;
import com.credapp.helper.ConnectionProvider;
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
public class TestReviewApplicationDao {
  
    
       static Connection con = null;
    static Application applicationInstance = new Application();
static  ReviewApplicationDao rdao = null;
    static ApplicationDao adao = null;
    static ArrayList<Application> reviewList = new ArrayList<>();
    static ArrayList<Application> applicationList = new ArrayList<>();
    static int aid;
//            static Logger LOG =null;
    static final Logger LOG = Logger.getLogger(TestPendingApplicationDao.class.getName());

    @BeforeClass
    public static void setUp() {

        con = ConnectionProvider.getConnection();
//        cdao = new CompanyDao(con);
  adao= new ApplicationDao(con);
rdao = new ReviewApplicationDao(con);
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
applicationInstance.setStatus("Under Review");
SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    Timestamp timestamp = Timestamp.valueOf(strDate);
applicationInstance.setApplicationDate(timestamp);
applicationInstance.setReason("Discount is not right.");


        System.out.println("running pending test casses");

    }
    
@Test
    public void testSubmitForm(){
        
                   Application application = new Application();

        System.out.println("submit under review form test case");
        boolean result=rdao.submitForm(applicationInstance);
Assert.assertTrue("submit under review form test case passed", result);
        application=rdao.getReviewApplicationById(applicationInstance.getId());
        Assert.assertEquals(applicationInstance.getStatus(), application.getStatus());
        Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
        Assert.assertEquals(applicationInstance.getId(), application.getId());

        System.out.println("submit under review form test case passed");
rdao.deleteReviewById(applicationInstance.getId());
    }
  
    
    
     @Test
    public void testDeleteReviewById() {
        
        
        try {
            System.out.println("delete review application ");

            boolean result = rdao.submitForm(applicationInstance);
            Assert.assertTrue("delete review application test case passed", result);
            System.out.println("application submitted in review table ");

            Application application = null;
            application=rdao.getReviewApplicationById(applicationInstance.getId());
            System.out.println(application);
            System.out.println(application.getId());
            Assert.assertEquals(applicationInstance.getId(),application.getId());
            Assert.assertNotNull(application);
            Assert.assertNotNull(application.getId());
      
            result=rdao.deleteReviewById(applicationInstance.getId());
            Assert.assertTrue("delete review application test case passed", result);
            
            application= rdao.getReviewApplicationById(applicationInstance.getId());
            System.out.println(application);
            System.out.println(application.getId());
            Assert.assertEquals(0,application.getId());
            Assert.assertNull(application.getCompanyName());
            
            
            Assert.assertNotEquals(applicationInstance.getId(), application.getId());
      
            
            System.out.println("delete review application test case passed");
        } catch (Exception e) {
        }
        
    }

     @Test
    public void testGetReviewApplicationsFromRangeByCompanyName(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Getting review applications from range by company name ");
        
boolean result=rdao.submitForm(applicationInstance);
Assert.assertTrue("submit review application form test case passed", result);
        
    
        int count;
        try {
            count = rdao.getReviewApplicationsCountByCompanyName(applicationInstance.getCompanyName());
    applicationList=rdao.getReviewApplicationsFromRangeByCompanyName(applicationInstance.getCompanyName(), 0, count);
Assert.assertEquals(count, applicationList.size());
for(Application applicationIns: applicationList){
application = applicationIns;
}
            System.out.println(application.getApplicationDate());
            System.out.println(application.getCompanyName());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
            System.out.println("Getting review applications from range by company name test case passed");
rdao.deleteReviewById(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TestPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     @Test
    public void testGetReviewApplicationsFromRange(){
        
               ArrayList<Application> applicationList = new ArrayList<>();
Application application = new Application();
            System.out.println("Getting review applications from range ");
        
boolean result=rdao.submitForm(applicationInstance);
Assert.assertTrue("submit review application form test case passed", result);
        
    
        int count;
        try {
    applicationList=rdao.getReviewApplicationsFromRange(0, 3);
            System.out.println(applicationList.size());
Assert.assertEquals(3, applicationList.size());
            count = rdao.getReviewApplicationsCount();
applicationList=rdao.getReviewApplicationsFromRange(0, count);
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
            System.out.println("Getting review applications from range test case passed");
rdao.deleteReviewById(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TestPendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   @Test
    public void testGetAllReviewApplications() {
        ResultSet rs = null;
        int count = 0;
                    System.out.println("Getting all review applications test case ");

        try {
            
            boolean result=rdao.submitForm(applicationInstance);
            Assert.assertTrue("submit review application", result);

            rs=rdao.getAllReviewApplications();

            Assert.assertNotNull(rs);

            while (rs.next()) {
                count++;

            }
            Assert.assertEquals(count, rdao.getReviewApplicationsCount());
            Application application = new Application();
            application= rdao.getReviewApplicationById(applicationInstance.getId());
            
            Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
     Assert.assertEquals(applicationInstance.getCompanyName(), application.getCompanyName());
     Assert.assertEquals(applicationInstance.getId(), application.getId());
            System.out.println("Getting all review applications test case passed");
           rdao.deleteReviewById(applicationInstance.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testGetReviewApplicationsCount() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting review applications count");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM reviewapplication");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            actualCount = rdao.getReviewApplicationsCount();
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting review applications count test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Test
    public void testGetReviewApplicationsCountByCompanyName() {
        int actualCount = 0;
        int expectedCount = 0;
                    System.out.println("Getting review applications count by company name");

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM reviewapplication where appCompanyName=\"Wipro\"");
            rs.next();
            expectedCount = (rs.getInt("count(*)"));
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            actualCount = rdao.getReviewApplicationsCountByCompanyName("Wipro");
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting review applications count by company name test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

@Test
    public void testGetReviewApplicationById(){
        
                   Application application = new Application();

        System.out.println("get under review application by id form test case");
        boolean result=rdao.submitForm(applicationInstance);
Assert.assertTrue("get under review application by id form test case passed", result);
        application=rdao.getReviewApplicationById(applicationInstance.getId());
        Assert.assertEquals(applicationInstance.getStatus(), application.getStatus());
        Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
        Assert.assertEquals(applicationInstance.getId(), application.getId());

        System.out.println("get under review application by id form test case passed");
rdao.deleteReviewById(applicationInstance.getId());
    }
  

@Test
    public void testGetAllReviewApplicationsByName(){
        
                   Application application = new Application();
ArrayList<Application> applicationList= new ArrayList<>();
ResultSet rs = null;
int count=0;
int expectedCount=0;
System.out.println("get under review application by name form test case");
        boolean result=rdao.submitForm(applicationInstance);
Assert.assertTrue("get under review application by name form test case passed", result);
        rs=rdao.getAllReviewApplicationsByName(applicationInstance.getCompanyName());
           try {
               while (rs.next()) {
                   
                   count++;
        application.setStatus(rs.getString("appStatus"));
        application.setApplicationDate(rs.getTimestamp("appDate"));
        application.setId(rs.getInt("appId"));
               }      
           
           expectedCount=rdao.getReviewApplicationsCountByCompanyName(applicationInstance.getCompanyName());
           Assert.assertEquals(expectedCount, count);
           } catch (SQLException ex) {
               Logger.getLogger(TestReviewApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
           }
        Assert.assertEquals(applicationInstance.getStatus(), application.getStatus());
        Assert.assertEquals(applicationInstance.getApplicationDate(), application.getApplicationDate());
        Assert.assertEquals(applicationInstance.getId(), application.getId());

        System.out.println("get under review application by name form test case passed");
rdao.deleteReviewById(applicationInstance.getId());
    }
  
    
       
    @AfterClass
    public static void tearDown() {
        try {
            con.close();
            System.out.println("connection closed");
            System.out.println("After class");
        } catch (SQLException ex) {
//                    Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
