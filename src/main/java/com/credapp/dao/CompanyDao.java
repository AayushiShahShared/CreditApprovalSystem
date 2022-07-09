package com.credapp.dao;

import com.credapp.entities.Application;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.credapp.entities.Company;
import java.sql.*;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

public class CompanyDao {

    private final Connection con;

    public CompanyDao(Connection con) {
        this.con = con;
    }
    
    // traverse all users/companies
public Company getCompanyByType(String cType) {
Company company = null;
ResultSet rs = null;
//    create query
String query ="SELECT * FROM company WHERE cType=?";
try{
//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1, cType);
//execute query
rs =pstmt.executeQuery();
    while (rs.next()) {
        company=new  Company();
                  company.setcId(rs.getInt("cId"));
                company.setcName(rs.getString("cName"));
                company.setcAge(rs.getInt("cAge"));
                company.setcOfficeLocs(rs.getString("cOfficeLocs"));
                company.setcEmail(rs.getString("cEmail"));
                company.setcAbout(rs.getString("cAbout"));
                company.setcRegDateTime(rs.getTimestamp("cRDate"));
                   company.setcRNo(rs.getInt("cRno"));
                   company.setcClass(rs.getString("cClass"));
                   company.setcCategory(rs.getString("cCategory"));
                   company.setcSize(rs.getInt("cSize"));
                   company.setcType(rs.getString("cType"));                   
                   company.setVerify(rs.getString("cVerify"));
                   company.setIsEnable(rs.getString("isEnable"));
       
    }
}
catch(SQLException e){
    
    System.out.println("error while geting companies");
}
return company;
}


// traverse all applications with pending state
public int getCompaniesCount() throws SQLException{

    int count=0;
//    create query
String query ="SELECT * FROM company where cType='regUser'";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
}
return  count;
}


   // traverse all users/companies
public ResultSet getAllCompanies() {

   ResultSet rs =null;
//    create query
String query ="SELECT * FROM company";
try{
//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
rs =pstmt.executeQuery();
}
catch(SQLException e){
    
    System.out.println("error while geting companies");
}
return rs;
}


   // traverse all users/companies
public ArrayList<Company> getAllCompaniesFromRange(int start, int recordCount) {

    
//    create an company list
    ArrayList<Company> companyList = new ArrayList<>();

//SELECT * FROM CARS WHERE COMPANY IN ('TOYOTA','HONDA');
//    create query
String query ="SELECT * FROM company WHERE cType IN ('fAnalyst','regUser') LIMIT ?,?";

try{

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//set the parameter
pstmt.setInt(1, start);
pstmt.setInt(2, recordCount);


//execute query
ResultSet rs =pstmt.executeQuery();


    while (rs.next()) {
//        create application object
     Company company = new Company();
     
//        set application object
       company.setcId(rs.getInt("cId"));
                   company.setcName(rs.getString("cName"));
                   company.setcRNo(rs.getInt("cRno"));
                   
                   company.setcClass(rs.getString("cClass"));
                   company.setcCategory(rs.getString("cCategory"));
                   company.setcAge(rs.getInt("cAge"));
                   
                   company.setcSize(rs.getInt("cSize"));
                   company.setcOfficeLocs(rs.getString("cOfficeLocs"));
                   company.setcEmail(rs.getString("cEmail"));
                   
                   company.setcAbout(rs.getString("cAbout"));
                   company.setcRegDateTime(rs.getTimestamp("cRDate"));
                   company.setcType(rs.getString("cType"));
                   
                   company.setVerify(rs.getString("cVerify"));
                   company.setIsEnable(rs.getString("isEnable"));
                   
                 
        companyList.add(company);
    }
}
catch(SQLException e){
    
    System.out.println("error while geting companies");
}
return companyList;
}



    public boolean updatePassword(String email, String newPass) {
        boolean updatePassword = false;

        try {

//11 fields total to insert into database 
//create query
            String query = "update company set cPassword=? where cEmail=?";

//    create prepared statement 
            PreparedStatement pstmt = this.con.prepareStatement(query);

//  set prepared statement
newPass = BCrypt.hashpw(newPass, BCrypt.gensalt(12));
            pstmt.setString(1, newPass);
            pstmt.setString(2, email);

//execute query
            pstmt.executeUpdate();
            updatePassword = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatePassword;
    }

    
    public boolean verifyCompany(Company company) {
        boolean verifyUpdate = false;

        try {

//11 fields total to insert into database 
//create query
            String query = "update company set cVerify=? where cEmail=?";

//    create prepared statement 
            PreparedStatement pstmt = this.con.prepareStatement(query);

//  set prepared statement
            pstmt.setString(1, company.getVerify());
            pstmt.setString(2, company.getcEmail());

//execute query
            pstmt.executeUpdate();
            verifyUpdate = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifyUpdate;
    }

    
    
//    Method to insert company detais to DataBase
//    Registration form
    public boolean registerCompany(Company company) {
        boolean insert = false;
String pwd=company.getcPassword();
pwd=BCrypt.hashpw(pwd, BCrypt.gensalt(12));
company.setcPassword(pwd);

        try {

//11 fields total to insert into database 
//create query
            String query = "insert into company(cName, cRno, cStatus, cCategory, cClass, cAge, cSize, cOfficeLocs, cEmail, cPassword, cAbout) values(?,?,?,?,?,?,?,?,?,?,?)";

//    create prepared statement 
            PreparedStatement pstmt = this.con.prepareStatement(query);

//  set prepared statement
            pstmt.setString(1, company.getcName());
            pstmt.setInt(2, company.getcRNo());
            pstmt.setString(3, company.getcStatus());
            pstmt.setString(4, company.getcCategory());
            pstmt.setString(5, company.getcClass());
            pstmt.setInt(6, company.getcAge());
            pstmt.setInt(7, company.getcSize());
            pstmt.setString(8, company.getcOfficeLocs());
            pstmt.setString(9, company.getcEmail());
            pstmt.setString(10, company.getcPassword());
            pstmt.setString(11, company.getcAbout());

//execute query
            pstmt.executeUpdate();
            insert = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insert;
    }


// get company name and password and check their credentials
    public Company getCompanyByNameAndPassword(String cname, String cpassword) {
        Company company = null;
boolean pwd_check=false;
//String pwd =cpassword;
//int id =0;
        try {
//            String query = "select * from company where cName=? and cPassword=?";
            String query = "select * from company where cName=?";

PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, cname);
//            cpassword = BCrypt.hashpw(cpassword, BCrypt.gensalt(12));
//            pstmt.setString(2, cpassword);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                company = new Company();
                
                company.setcPassword(rs.getString("cPassword"));
//        id=rs.getInt("id");

pwd_check=BCrypt.checkpw(cpassword, company.getcPassword());

if(pwd_check){
                company.setcId(rs.getInt("cId"));
                company.setcName(rs.getString("cName"));
                company.setcRNo(rs.getInt("cRno"));
                company.setcStatus(rs.getString("cStatus"));
                company.setcCategory(rs.getString("cCategory"));
                company.setcClass(rs.getString("cClass"));
                company.setcAge(rs.getInt("cAge"));
                company.setcSize(rs.getInt("cSize"));
                company.setcOfficeLocs(rs.getString("cOfficeLocs"));
                company.setcEmail(rs.getString("cEmail"));
                company.setcAbout(rs.getString("cAbout"));
                company.setcRegDateTime(rs.getTimestamp("cRDate"));
                company.setVerify(rs.getString("cVerify"));
                company.setcType(rs.getString("cType"));
                company.setIsEnable(rs.getString("isEnable"));
}            
}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;

    }
    
//get the company object and update company details
    public boolean updateCompanyDetails(Company company) {
        boolean update = false;
        try {
//        create query to update
            String query = "update company set cName=? , cStatus=?, cCategory=?, cClass=?, cAge=?, cSize=?, cOfficeLocs=?, cEmail=?, cPassword=?, cAbout=? where cId=?";

//    create prepared statement
            PreparedStatement pstmt = con.prepareStatement(query);

//    set the prepared statement,set the updated valued from company object
            pstmt.setString(1, company.getcName());
            pstmt.setString(2, company.getcStatus());
            pstmt.setString(3, company.getcCategory());
            pstmt.setString(4, company.getcClass());
            pstmt.setInt(5, company.getcAge());
            pstmt.setInt(6, company.getcSize());
            pstmt.setString(7, company.getcOfficeLocs());
            pstmt.setString(8, company.getcEmail());
            pstmt.setString(9, company.getcPassword());
            pstmt.setString(10, company.getcAbout());
            pstmt.setInt(11, company.getcId());

//execute query
            pstmt.executeUpdate();

//return true after successfull updation
            update = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }
    
 //delete company by name
public boolean updateDisbleCompanyByName(String companyName,String isEnable) {
boolean update= false;
try{
//    create query
String query ="UPDATE company set isEnable=? where cName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,isEnable);
pstmt.setString(2,companyName);


//execute query
pstmt.executeUpdate();

update=true;
}
catch(SQLException e){
    System.out.println("sql error while deleting");
}
return update;

    
}
 
   
 //delete company by name
public boolean deleteCompanyByName(String companyName) {
boolean delete= false;
try{
//    create query
String query ="DELETE FROM company where cName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,companyName);


//execute query
pstmt.executeUpdate();

delete=true;
}
catch(SQLException e){
    System.out.println("sql error while deleting");
}
return delete;

    
}
   


    public String getEmailByName(String comName) {
//Application applicationInstance =  new Application();
String comEmail= "";
try{
//    create query
String query ="SELECT * FROM company where cName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,comName);
//execute query
ResultSet rs =pstmt.executeQuery();
//if(rs!=null){
    while(rs.next()){
comEmail=rs.getString("cEmail");
    }}
catch(SQLException e){
    System.out.println("error while getting data by id");
}
return comEmail;

    
}
















    
    public Company getCompanyByEmail(String comEmail) {
Company company =  new Company();
try{
//    create query
String query ="SELECT * FROM company where cEmail=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,comEmail);
//execute query
ResultSet rs =pstmt.executeQuery();
//if(rs!=null){
    while(rs.next()){
        company.setcId(rs.getInt("cId"));
                company.setcName(rs.getString("cName"));
                company.setcRNo(rs.getInt("cRno"));
                company.setcStatus(rs.getString("cStatus"));
                company.setcCategory(rs.getString("cCategory"));
                company.setcClass(rs.getString("cClass"));
                company.setcAge(rs.getInt("cAge"));
                company.setcSize(rs.getInt("cSize"));
                company.setcOfficeLocs(rs.getString("cOfficeLocs"));
                company.setcEmail(rs.getString("cEmail"));
                company.setcPassword(rs.getString("cPassword"));
                company.setcAbout(rs.getString("cAbout"));
                company.setcRegDateTime(rs.getTimestamp("cRDate"));
                company.setVerify(rs.getString("cVerify"));
                company.setcType(rs.getString("cType"));
                company.setIsEnable(rs.getString("isEnable"));
    }}
catch(SQLException e){
    System.out.println("error while getting data by id");
}
return company;

    
}
    
    
    public Company getCompanyById(int id) {
Company company =  new Company();
try{
//    create query
String query ="SELECT * FROM company where cId=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setInt(1,id);
//execute query
ResultSet rs =pstmt.executeQuery();
//if(rs!=null){
    while(rs.next()){
        company.setcId(rs.getInt("cId"));
                company.setcName(rs.getString("cName"));
                company.setcRNo(rs.getInt("cRno"));
                company.setcStatus(rs.getString("cStatus"));
                company.setcCategory(rs.getString("cCategory"));
                company.setcClass(rs.getString("cClass"));
                company.setcAge(rs.getInt("cAge"));
                company.setcSize(rs.getInt("cSize"));
                company.setcOfficeLocs(rs.getString("cOfficeLocs"));
                company.setcEmail(rs.getString("cEmail"));
                company.setcPassword(rs.getString("cPassword"));
                company.setcAbout(rs.getString("cAbout"));
                company.setcRegDateTime(rs.getTimestamp("cRDate"));
                company.setVerify(rs.getString("cVerify"));
                company.setcType(rs.getString("cType"));
                company.setIsEnable(rs.getString("isEnable"));
    }}
catch(SQLException e){
    System.out.println("error while getting data by id");
}
return company;

    
}
    

}
