/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.dao;

import com.credapp.entities.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aayushi
 */
public class ReviewApplicationDao {
 
    private Connection con;

public ReviewApplicationDao(Connection con){
this.con=con;
}
    
    //if form approved so use this method
public boolean submitForm(Application application){
    boolean submit=false;
    try {
//       create query  
        String query = "insert into reviewapplication(appId,appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appTotalAmt,appInterest,appStatus,appDate,appReason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
//        create statement
        PreparedStatement pstmt =this.con.prepareStatement(query);
        
//        set the values to prepared statement
pstmt.setInt(1, application.getId());
pstmt.setString(2, application.getCompanyName());
pstmt.setInt(3, application.getLoanAmount());
pstmt.setInt(4, application.getPeriod());
pstmt.setInt(5, application.getDiscount());
pstmt.setInt(6, application.getCibil());
pstmt.setString(7, application.getBankAcc());
pstmt.setInt(8, application.getRevenue());
pstmt.setInt(9, application.getEmpExpense());
pstmt.setFloat(10, application.getTotalAmt());
pstmt.setFloat(11, application.getInterest());
pstmt.setString(12, application.getStatus());
pstmt.setTimestamp(13, application.getApplicationDate());
pstmt.setString(14, application.getReason());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}


//delete by id
public boolean deleteReviewById(int aid) {
boolean delete= false;
try{
//    create query
String query ="DELETE FROM reviewapplication where appId =?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setInt(1,aid);


//execute query
pstmt.executeUpdate();

delete=true;
}
catch(SQLException e){
    System.out.println("sql errror while deleting");
}
return delete;

    
}

// traverse all applications
//public List<Application> getAllReviewApplications() throws SQLException{
//List<Application> applicationList = new ArrayList<>();
////    create query
//String query ="SELECT * FROM reviewapplication";
//
////create prepared statement
//PreparedStatement pstmt = con.prepareStatement(query);
//
////execute query
//ResultSet rs =pstmt.executeQuery();
//Application applicationInstance  = new Application();
//    while(rs.next()){
//       applicationInstance.setId(rs.getInt("appId"));
//                    applicationInstance.setCompanyName(rs.getString("appCompanyName"));
//                    applicationInstance.setLoanAmount(rs.getInt("appLoanAmount"));
//                    applicationInstance.setPeriod(rs.getInt("appPeriod"));
//                    applicationInstance.setRate(rs.getInt("appRate"));
//                    applicationInstance.setCibil(rs.getInt("appCibil"));
//                    applicationInstance.setBankAcc(rs.getString("appBankAcc"));
//                    applicationInstance.setRevenue(rs.getInt("appRevenue"));
//                    applicationInstance.setEmpExpense(rs.getInt("appEmpExpense"));
//                    applicationInstance.setApplicationDate(rs.getTimestamp("appDate"));
//    applicationList.add(applicationInstance);
//    }
//return applicationList;
//}


// traverse all applications
public ArrayList<Application> getReviewApplicationsFromRangeByCompanyName(String companyName,int start, int recordCount) throws SQLException{

//    create an application list
    ArrayList<Application> applicationList = new ArrayList<>();

//    create query
String query ="SELECT * FROM reviewapplication WHERE appCompanyName= ? LIMIT ?,?";
//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//set the parameter
pstmt.setString(1, companyName);
pstmt.setInt(2, start);
pstmt.setInt(3, recordCount);

//execute query
ResultSet rs =pstmt.executeQuery();

    while (rs.next()) {
//        create application object
        Application applicationInstance = new Application();
        
//        set application object
       applicationInstance.setId(rs.getInt("appId"));
                    applicationInstance.setCompanyName(rs.getString("appCompanyName"));
                    applicationInstance.setLoanAmount(rs.getInt("appLoanAmount"));
                    applicationInstance.setPeriod(rs.getInt("appPeriod"));
                    applicationInstance.setDiscount(rs.getInt("appDiscount"));
                    applicationInstance.setCibil(rs.getInt("appCibil"));
                    applicationInstance.setBankAcc(rs.getString("appBankAcc"));
                    applicationInstance.setRevenue(rs.getInt("appRevenue"));
                    applicationInstance.setEmpExpense(rs.getInt("appEmpExpense"));
                    applicationInstance.setTotalAmt(rs.getFloat("appTotalAmt"));
                    applicationInstance.setInterest(rs.getFloat("appInterest"));
                    applicationInstance.setStatus(rs.getString("appStatus"));
                    applicationInstance.setApplicationDate(rs.getTimestamp("appDate"));
                    applicationInstance.setComment(rs.getString("appComment"));
                    applicationInstance.setReason(rs.getString("appReason"));
 
        applicationList.add(applicationInstance);
    }
return applicationList;
}

// traverse all applications
public ArrayList<Application> getReviewApplicationsFromRange(int start, int recordCount) throws SQLException{

//    create an application list
    ArrayList<Application> applicationList = new ArrayList<>();

//    create query
String query ="SELECT * FROM reviewapplication LIMIT ?,?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//set the parameter
pstmt.setInt(1, start);
pstmt.setInt(2, recordCount);

//execute query
ResultSet rs =pstmt.executeQuery();

    while (rs.next()) {
//        create application object
        Application applicationInstance = new Application();
        
//        set application object
       applicationInstance.setId(rs.getInt("appId"));
                    applicationInstance.setCompanyName(rs.getString("appCompanyName"));
                    applicationInstance.setLoanAmount(rs.getInt("appLoanAmount"));
                    applicationInstance.setPeriod(rs.getInt("appPeriod"));
                    applicationInstance.setDiscount(rs.getInt("appDiscount"));
                    applicationInstance.setCibil(rs.getInt("appCibil"));
                    applicationInstance.setBankAcc(rs.getString("appBankAcc"));
                    applicationInstance.setRevenue(rs.getInt("appRevenue"));
                    applicationInstance.setEmpExpense(rs.getInt("appEmpExpense"));
                    applicationInstance.setTotalAmt(rs.getFloat("appTotalAmt"));
                    applicationInstance.setInterest(rs.getFloat("appInterest"));
                    applicationInstance.setStatus(rs.getString("appStatus"));
                    applicationInstance.setApplicationDate(rs.getTimestamp("appDate"));
                    applicationInstance.setComment(rs.getString("appComment"));
                    applicationInstance.setReason(rs.getString("appReason"));
 
        applicationList.add(applicationInstance);
    }
return applicationList;
}

public ResultSet getAllReviewApplications() {
ResultSet rs =null;
//    create query
String query ="SELECT * FROM reviewapplication";
try{
//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
rs =pstmt.executeQuery();
}
catch(SQLException e){
    System.out.println("eroor while geting applications");
}
return rs;
}


public Application getById(int aid) throws SQLException{
Application applicationInstance =  new Application();

//    create query
String query ="SELECT * FROM reviewapplication where appId =?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setInt(1,aid);
//execute query
ResultSet rs =pstmt.executeQuery();
//if(rs!=null){
    while(rs.next()){
       applicationInstance.setId(rs.getInt("appId"));
                    applicationInstance.setCompanyName(rs.getString("appCompanyName"));
                    applicationInstance.setLoanAmount(rs.getInt("appLoanAmount"));
                    applicationInstance.setPeriod(rs.getInt("appPeriod"));
                    applicationInstance.setDiscount(rs.getInt("appDiscount"));
                    applicationInstance.setCibil(rs.getInt("appCibil"));
                    applicationInstance.setBankAcc(rs.getString("appBankAcc"));
                    applicationInstance.setRevenue(rs.getInt("appRevenue"));
                    applicationInstance.setEmpExpense(rs.getInt("appEmpExpense"));
                    applicationInstance.setTotalAmt(rs.getFloat("appTotalAmt"));
                    applicationInstance.setInterest(rs.getFloat("appInterest"));
                    applicationInstance.setStatus(rs.getString("appStatus"));
                    applicationInstance.setApplicationDate(rs.getTimestamp("appDate"));
                     applicationInstance.setReason(rs.getString("appReason"));

    }
return applicationInstance;

    
}







// traverse all applications with pending state
public int getReviewApplicationsCount() throws SQLException{

    int count=0;
//    create query
String query ="SELECT * FROM reviewapplication";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
}


return count;
}

// traverse all applications with pending state
public int getReviewApplicationsCountByCompanyName(String companyName) throws SQLException{

    int count=0;
//    create query
String query ="SELECT * FROM reviewapplication WHERE appCompanyName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

pstmt.setString(1, companyName);
//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
}


return count;
}

// traverse particular company applications
public Application getReviewApplicationById(int appid) {

    
//        create application object
        Application applicationInstance = new Application();
        
    
//    create query
String query ="SELECT * FROM reviewapplication WHERE appId=?";

//create prepared statement
PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(query);


// set the values to prepared statement
pstmt.setInt(1, appid);

//execute query
ResultSet rs =pstmt.executeQuery();

if(rs.next()){
    
    
//        set application object
       applicationInstance.setId(rs.getInt("appId"));
                    applicationInstance.setCompanyName(rs.getString("appCompanyName"));
                    applicationInstance.setLoanAmount(rs.getInt("appLoanAmount"));
                    applicationInstance.setPeriod(rs.getInt("appPeriod"));
                    applicationInstance.setDiscount(rs.getInt("appDiscount"));
                    applicationInstance.setCibil(rs.getInt("appCibil"));
                    applicationInstance.setBankAcc(rs.getString("appBankAcc"));
                    applicationInstance.setRevenue(rs.getInt("appRevenue"));
                    applicationInstance.setEmpExpense(rs.getInt("appEmpExpense"));
                    applicationInstance.setTotalAmt(rs.getFloat("appTotalAmt"));
                    applicationInstance.setInterest(rs.getFloat("appInterest"));
                    applicationInstance.setStatus(rs.getString("appStatus"));
                    applicationInstance.setApplicationDate(rs.getTimestamp("appDate"));
                    applicationInstance.setComment(rs.getString("appComment"));
                    applicationInstance.setReason(rs.getString("appReason"));
 
    
    
}

        } catch (SQLException ex) {
            Logger.getLogger(ReviewApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
return applicationInstance;
}

// traverse all applications with pending state
public ResultSet getAllReviewApplicationsByName(String companyName) {

//    create query
String query ="SELECT * FROM reviewapplication WHERE appCompanyName=?";

//create prepared statement
PreparedStatement pstmt;
ResultSet rs = null;
try {
            pstmt = con.prepareStatement(query);
pstmt.setString(1, companyName);
//execute query
rs =pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ReviewApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }


return rs;
}

}
