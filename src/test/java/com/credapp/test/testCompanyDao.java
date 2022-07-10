/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.test;

import com.credapp.dao.CompanyDao;
import com.credapp.entities.Application;
import com.credapp.entities.Company;
import com.credapp.helper.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Aayushi
 */
public class TestCompanyDao {

    static Connection con = null;

//    static ApplicationDao adao = null;
    static CompanyDao cdao = null;
//    static ReviewApplicationDao rdao = null;
    static ArrayList<Application> companyList = new ArrayList<>();
    static int cid;
//            static Logger LOG =null;
    static final Logger LOG = Logger.getLogger(TestCompanyDao.class.getName());

    @BeforeClass
    public static void setUp() {

        con = ConnectionProvider.getConnection();
        cdao = new CompanyDao(con);
//  rdao= new ReviewApplicationDao(con);

// try {
//            companyList=cdao.getReviewApplicationsFromRange(0, 1);
//            companyList=cdao.getAllCompaniesFromRange(0, 1);
//            for(Application applicationIns : List){
//      applicationIns.setId(applicationIns.getId());
//                aid = applicationIns.getId();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(testApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        System.out.println("before class");
        System.out.println(con);
//        System.out.println(aid);
    }

    @Test
    public void testRegisterCompany() {

        Company company = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");

        try {
            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            System.out.println("company registration test case passed");
            cdao.deleteCompanyByName(company.getcName());
//            Assert.assertEquals(cid, cdao);
        } catch (Exception e) {
        }

    }

    @Test
    public void testUpdatePassword() {

        Company company = new Company();
        Company companyInstance = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");

        try {
            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            System.out.println("company registred");
//            Assert.assertEquals(cid, cdao);
            String newPass = "newPass@123";
            result = cdao.updatePassword("xyz@gmail.com", newPass);
            Assert.assertTrue("password updation test case passed", result);
            System.out.println("password updation test case passed ");

            companyInstance = cdao.getCompanyByEmail("xyz@gmail.com");
//newPass=BCrypt.hashpw(newPass, BCrypt.gensalt(12));
            BCrypt.checkpw(newPass, companyInstance.getcPassword());
//            Assert.assertEquals(newPass, companyInstance.getcPassword());
            System.out.println("new password updated");

            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testGetCompanyByType() {
        Company company = new Company();
        Company companyInstance = new Company();
        company.setcType("fAnalyst");
        try {
            companyInstance = cdao.getCompanyByType(company.getcType());
            Assert.assertNotNull(companyInstance);
            System.out.println("getting company by its account type ");
            Assert.assertEquals("credappdemo@gmail.com", companyInstance.getcEmail());
            Assert.assertEquals("fAnalyst", companyInstance.getcType());
            Assert.assertEquals("fa", companyInstance.getcName());
            System.out.println("getting company by its account type test case passed");

        } catch (Exception e) {
        }
    }

    @Test
    public void testGetCompaniesCount() {
        int actualCount = 0;
        int expectedCount = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from company");
            rs.next();
            expectedCount = (rs.getInt("count(*)")) - 2;
            System.out.println("Table contains " + rs.getInt("count(*)") + " rows");

            actualCount = cdao.getCompaniesCount();
            System.out.println("Table contains " + actualCount + " rows");
            Assert.assertEquals(expectedCount, actualCount);
            System.out.println("Getting companies count test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testVerifyCompany() {

        Company company = new Company();
        Company companyInstance = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("no");
        company.setIsEnable("true");

        try {
            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            System.out.println("company registred");
            companyInstance = cdao.getCompanyByEmail(company.getcEmail());
            Assert.assertEquals("no", companyInstance.getVerify());
            company.setVerify("yes");
            result = cdao.verifyCompany(company);
            Assert.assertTrue("verification updation test case passed", result);
            companyInstance = cdao.getCompanyByEmail(company.getcEmail());
            Assert.assertNotNull(companyInstance);
            System.out.println("verification updated");
            Assert.assertEquals("yes", companyInstance.getVerify());
            System.out.println("verification updation test case passed");
            cdao.deleteCompanyByName(companyInstance.getcName());

            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testGetAllCompanies() {
        ResultSet rs = null;
        rs = cdao.getAllCompanies();
        int count = 0;
        try {
            Assert.assertNotNull(rs);

            while (rs.next()) {
                count++;

            }
            Company company = new Company();
            company = cdao.getCompanyByEmail("dshah742000@gmail.com");
            Assert.assertEquals("dshah742000@gmail.com", company.getcEmail());
            Assert.assertEquals("Wipro", company.getcName());
            Assert.assertEquals(cdao.getCompaniesCount(), count - 2);
            System.out.println("Getting all companies test case passed");
        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetAllCompaniesFromRange() {

        ArrayList<Company> companyList = new ArrayList<>();
        Company companyInstance = null;
        int count = 0;
        companyList = cdao.getAllCompaniesFromRange(0, 3);
        Assert.assertEquals(3, companyList.size());
        try {
            count = cdao.getCompaniesCount() + 1;
            companyList = cdao.getAllCompaniesFromRange(0, count);
            System.out.println(companyList.size());
            Assert.assertEquals(count, companyList.size());
            for (Company company : companyList) {
                if (company.getcType().equalsIgnoreCase("fAnalyst")) {
                    companyInstance = company;
                }
            }
            Assert.assertEquals("credappdemo@gmail.com", companyInstance.getcEmail());
            Assert.assertEquals("fa", companyInstance.getcName());
            System.out.println("Getting all companies from range test case passed");

        } catch (SQLException ex) {
            Logger.getLogger(TestCompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetCompanyByNameAndPassword() {

        Company company = new Company();
        Company companyInstance = null;
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("get company by name and password");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            System.out.println("company registred");
            company.setcPassword("xyz@123");
//            Company companyInstance = cdao.getCompanyByNameAndPassword(company.getcName(),company.getcPassword());
            companyInstance = cdao.getCompanyByNameAndPassword(company.getcName(), company.getcPassword());
            Assert.assertNotNull(companyInstance);
            Assert.assertEquals(company.getcName(), companyInstance.getcName());
            Assert.assertTrue("get company by id test case passed", result);
            System.out.println("get company by id test case passed");
            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testUpdateCompanyDetails() {

        Company company = new Company();
        Company companyInstance = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("update company details");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            System.out.println("company registred");
            company.setcPassword("xyz@123");
//    .................................................           
            companyInstance.setcName("xyz");
            companyInstance.setcAge(1);
            companyInstance.setcOfficeLocs("pune mumbai");
            companyInstance.setcEmail("xyz@gmail.com");
            companyInstance.setcPassword("xyz@123");
            companyInstance.setcRNo(134);
            companyInstance.setcCategory("shares");
            companyInstance.setcType("regUser");
            companyInstance.setVerify("yes");
            companyInstance.setIsEnable("true");
//            companyInstance = company;
            companyInstance.setcAbout("new xyz company");
            companyInstance.setcStatus("no");
            companyInstance.setcClass("public");
            companyInstance.setcSize(23);

            result = cdao.updateCompanyDetails(companyInstance);
            Assert.assertTrue("update company details test case passed", result);
            System.out.println(company.getcAbout());
            System.out.println(companyInstance.getcAbout());

            Assert.assertNotEquals(company.getcAbout(), companyInstance.getcAbout());
            Assert.assertNotEquals(company.getcStatus(), companyInstance.getcStatus());
            Assert.assertNotEquals(company.getcSize(), companyInstance.getcSize());
            Assert.assertNotEquals(company.getcClass(), companyInstance.getcClass());
            System.out.println("update company details test case passed");
            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testUpdateDisbleCompanyByName() {

        Company company = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("disable company ");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            Assert.assertEquals("true", company.getIsEnable());

            System.out.println("company registred");
//    .................................................           

            company.setIsEnable("false");

            result = cdao.updateDisbleCompanyByName(company.getcName(), company.getIsEnable());
            Assert.assertTrue("disable company test case passed", result);
            Assert.assertEquals("false", company.getIsEnable());
            System.out.println("disable company test case passed");
            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testDeleteCompanyByName() {

        Company company = new Company();
        Company companyInstance = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("delete company ");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration test case passed", result);
            System.out.println("company registred");

            result = cdao.deleteCompanyByName(company.getcName());
            Assert.assertTrue("delete company test case passed", result);
            companyInstance = cdao.getCompanyByEmail(company.getcEmail());
            Assert.assertNull(companyInstance.getcName());
            Assert.assertNull(companyInstance.getcPassword());
            System.out.println("delete company test case passed");
        } catch (Exception e) {
        }

    }

    @Test
    public void testGetEmailByName() {

        Company company = new Company();
        Company companyInstance = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("Getting email by name ");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration ", result);
            System.out.println("company registred");

            companyInstance.setcEmail(cdao.getEmailByName(company.getcName()));
            Assert.assertNotNull(companyInstance.getcEmail());
            Assert.assertEquals("getting email by name test case passed", company.getcEmail(), companyInstance.getcEmail());
            System.out.println("getting email by name test case passed");
            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testGetCompanyByEmail() {

        Company company = new Company();
        Company companyInstance = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("Getting company by email ");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration ", result);
            System.out.println("company registred");

            companyInstance = cdao.getCompanyByEmail("xyz@gmail.com");
            Assert.assertNotNull(companyInstance);
            Assert.assertEquals(company.getcName(), companyInstance.getcName());
            Assert.assertEquals(company.getcPassword(), companyInstance.getcPassword());
            System.out.println("getting company by email test case passed");
            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

    @Test
    public void testGetCompanyById() {

        Company company = new Company();
        Company companyInstance = new Company();
        Company companyObject = new Company();
        company.setcName("xyz");
        company.setcAge(1);
        company.setcOfficeLocs("pune mumbai");
        company.setcEmail("xyz@gmail.com");
        company.setcPassword("xyz@123");
        company.setcAbout("xyz company");
        company.setcRNo(134);
        company.setcStatus("yes");
        company.setcClass("Private");
        company.setcCategory("shares");
        company.setcSize(12);
        company.setcType("regUser");
        company.setVerify("yes");
        company.setIsEnable("true");
        try {
            System.out.println("Getting company by id ");

            boolean result = cdao.registerCompany(company);
            Assert.assertTrue("company registration ", result);
            System.out.println("company registred");

            companyInstance = cdao.getCompanyByEmail(company.getcEmail());
            int id =companyInstance.getcId();
            companyInstance = cdao.getCompanyById(id);
            
            
            Assert.assertNotNull(companyInstance);
            Assert.assertEquals(company.getcName(), companyInstance.getcName());
            Assert.assertEquals(company.getcPassword(), companyInstance.getcPassword());
            Assert.assertEquals(company.getcEmail(), companyInstance.getcEmail());
            System.out.println("getting company by id test case passed");
            cdao.deleteCompanyByName(company.getcName());
        } catch (Exception e) {
        }

    }

 
}
