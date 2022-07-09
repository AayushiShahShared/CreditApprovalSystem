
package com.credapp.dao;

import com.credapp.entities.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdk.jfr.Period;

public class ApplicationDao {

private Connection con;

public ApplicationDao(Connection con){
this.con=con;
}


// this query used to insert directly in application table
        final String approvedApplicationQuery = "insert into application(appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appTotalAmt,appInterest,appStatus,appReason) values(?,?,?,?,?,?,?,?,?,?,?,?)";
       

//if form approved so use this method
public boolean submitPendingForm(Application application){
    boolean submit=false;
    try {
//       create query  
        String query = "insert into application(appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appStatus,appReason) values(?,?,?,?,?,?,?,?,?,?)";
        
//        create statement
        PreparedStatement pstmt =con.prepareStatement(query);
        
//        set the values to prepared statement
pstmt.setString(1, application.getCompanyName());
pstmt.setInt(2, application.getLoanAmount());
pstmt.setInt(3, application.getPeriod());
pstmt.setInt(4, application.getDiscount());
pstmt.setInt(5, application.getCibil());
pstmt.setString(6, application.getBankAcc());
pstmt.setInt(7, application.getRevenue());
pstmt.setInt(8, application.getEmpExpense());
pstmt.setString(9, application.getStatus());
pstmt.setString(10, application.getReason());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}

//if form approved so use this method
//public boolean submitPendingFormAndGetId(Application application){
//    boolean submit=false;
//    try {
////       create query  
//        String query = "insert into application(appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appStatus,appReason) values(?,?,?,?,?,?,?,?,?)";
//        
////        create statement
//        PreparedStatement pstmt =con.prepareStatement(query);
//        
////        set the values to prepared statement
//pstmt.setString(1, application.getCompanyName());
//pstmt.setInt(2, application.getLoanAmount());
//pstmt.setInt(3, application.getPeriod());
//pstmt.setInt(4, application.getRate());
//pstmt.setInt(5, application.getCibil());
//pstmt.setString(6, application.getBankAcc());
//pstmt.setInt(7, application.getRevenue());
//pstmt.setInt(8, application.getEmpExpense());
//pstmt.setString(9, application.getStatus());
//
////execute the query
//pstmt.executeUpdate();
//
////set the boolean value to true if done 
//submit=true;
//    } catch (Exception e) {
//    e.printStackTrace();
//    }
//return submit;
//}
//

// traverse all applications with approved state
public int getApprovedApplicationsCount() throws SQLException{
    int count=0;
//    create query
String query ="SELECT * FROM application WHERE appStatus=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,"Approved");
//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
}


return count;
}

// traverse all applications with Rejected state
public int getRejectedApplicationsCount() throws SQLException{
    int count=0;
//    create query
String query ="SELECT * FROM application WHERE appStatus=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,"Rejected");
//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
//    System.out.println(count);
}


return count;
}



// traverse all applications with Rejected state
public int getAllApplicationsCount() throws SQLException{
    int count=0;
//    create query
String query ="SELECT * FROM application";

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
public int getApplicationsCount(String companyName) throws SQLException{
    int count=0;
//    create query
String query ="SELECT * FROM application WHERE appCompanyName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);
pstmt.setString(1,companyName);
//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
}


return count;
}


//if form approved so use this method
public boolean submitApprovedForm(Application application){
    boolean submit=false;
    try {

        
//          create query  used to insert directly in application table
//        String query = "insert into application(appId,appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appTotalAmt,appInterest,appStatus,appDate,appComment,appReason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String query = "insert into application(appId,appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appTotalAmt,appInterest,appStatus,appDate,appComment,appReason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//        create statement
        PreparedStatement pstmt =con.prepareStatement(query);
        
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
pstmt.setString(14, application.getComment());
pstmt.setString(15, application.getReason());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}

//if form approved so use this method
public boolean submitForm(Application application){
    boolean submit=false;
    try {

        
//          create query  used to insert directly in application table
        String query = 
"insert into application(appId,appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appTotalAmt,appInterest,appStatus,appDate,appComment,appReason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//        create statement
        PreparedStatement pstmt =con.prepareStatement(query);
        
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
pstmt.setString(14, application.getComment());
pstmt.setString(15, application.getReason());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}






//if form approved so use this method
public boolean submitApprovedFormInApplication(Application application){
    boolean submit=false;
    try {

        
//        create statement
        PreparedStatement pstmt =con.prepareStatement(approvedApplicationQuery);
        
//        set the values to prepared statement




pstmt.setString(1, application.getCompanyName());
pstmt.setInt(2, application.getLoanAmount());
pstmt.setInt(3, application.getPeriod());
pstmt.setInt(4, application.getDiscount());
pstmt.setInt(5, application.getCibil());
pstmt.setString(6, application.getBankAcc());
pstmt.setInt(7, application.getRevenue());
pstmt.setInt(8, application.getEmpExpense());
pstmt.setFloat(9, application.getTotalAmt());
pstmt.setFloat(10, application.getInterest());
pstmt.setString(11, application.getStatus());
pstmt.setString(12, application.getReason());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}


//if form rejected so use this method
public boolean submitRejectedFormInApplication(Application application){
    boolean submit=false;
    try {
        
//       create query  
        String query = "insert into application(appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appStatus,appReason) values(?,?,?,?,?,?,?,?,?,?)";
        
//        create statement
        PreparedStatement pstmt =con.prepareStatement(query);
        
//        set the values to prepared statement
pstmt.setString(1, application.getCompanyName());
pstmt.setInt(2, application.getLoanAmount());
pstmt.setInt(3, application.getPeriod());
pstmt.setInt(4, application.getDiscount());
pstmt.setInt(5, application.getCibil());
pstmt.setString(6, application.getBankAcc());
pstmt.setInt(7, application.getRevenue());
pstmt.setInt(8, application.getEmpExpense());
pstmt.setString(9, application.getStatus());
pstmt.setString(10, application.getReason());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}




//if form rejected so use this method
public boolean submitRejectedForm(Application application){
    boolean submit=false;
    try {
        
//       create query  
        String query = "insert into application(appId,appCompanyName,appLoanAmount,appPeriod,appRate,appCibil,appBankAcc,appRevenue,appEmpExpense,appStatus,appDate,appComment) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        
//        create statement
        PreparedStatement pstmt =con.prepareStatement(query);
        
//        set the values to prepared statement
pstmt.setInt(1, application.getId());
pstmt.setString(2, application.getCompanyName());
pstmt.setInt(3, application.getLoanAmount());
pstmt.setInt(4, application.getPeriod());
pstmt.setInt(5, application.getRate());
pstmt.setInt(6, application.getCibil());
pstmt.setString(7, application.getBankAcc());
pstmt.setInt(8, application.getRevenue());
pstmt.setInt(9, application.getEmpExpense());
pstmt.setString(10, application.getStatus());
pstmt.setTimestamp(11, application.getApplicationDate());
pstmt.setString(12, application.getComment());

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
submit=true;
    } catch (Exception e) {
    e.printStackTrace();
    }
return submit;
}


// traverse particular company applications
public ResultSet getApplications(String companyName) throws SQLException{

//    create query
String query ="SELECT * FROM application WHERE appCompanyName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

// set the values to prepared statement
pstmt.setString(1, companyName);

//execute query
ResultSet rs =pstmt.executeQuery();

return rs;
}







// traverse particular company applications
public Application getApplicationById(int appid) throws SQLException{

    
//        create application object
        Application applicationInstance = new Application();
        
    
//    create query
String query ="SELECT * FROM application WHERE appId=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

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
return applicationInstance;
}


// traverse all applications
public ResultSet getAllApplications() throws SQLException{

//    create query
String query ="SELECT * FROM application";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
ResultSet rs =pstmt.executeQuery();

return rs;
}








// traverse all applications
public ArrayList<Application> getAllApplicationsFromRange(int start, int recordCount) {

//    create an application list
    ArrayList<Application> applicationList = new ArrayList<>();

    
//    create query
String query ="SELECT * FROM application LIMIT ?,?";

    
//    create query
//String query ="SELECT * FROM application";

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
}
catch(SQLException e){
    System.out.println("Does not application from range");
}
return applicationList;
}


// traverse all applications
public ArrayList<Application> getAllApplicationsFromRange(String companyName,int start, int recordCount) throws SQLException{

//    create an application list
    ArrayList<Application> applicationList = new ArrayList<>();

//    create query
String query ="SELECT * FROM application WHERE appCompanyName= ? LIMIT ?,?";

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

// traverse application by all details
public Application getApplicationByDetails(String aName,int aloan,int aperiod,int adiscount,int acibil,String abankacc,int arevenue,int aempexpence) throws SQLException{
Application applicationInstance = new Application();
//    create query
String query ="SELECT * FROM application WHERE appCompanyName=? and appLoanAmount=? and appPeriod=? and appDiscount=? and appCibil=? "
        + "and appBankAcc=? and "
        + "appRevenue=? and appEmpExpense=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

pstmt.setString(1, aName);
pstmt.setInt(2, aloan);
pstmt.setInt(3, aperiod);
pstmt.setInt(4, adiscount);
pstmt.setInt(5, acibil);
pstmt.setString(6, abankacc);
pstmt.setInt(7, arevenue);
pstmt.setInt(8, aempexpence);

//execute query
ResultSet rs =pstmt.executeQuery();
    while (rs.next()) {
        
          applicationInstance.setId(rs.getInt("appId"));
                    applicationInstance.setCompanyName(rs.getString("appCompanyName"));
                    applicationInstance.setLoanAmount(rs.getInt("appLoanAmount"));
                    applicationInstance.setPeriod(rs.getInt("appPeriod"));
                    applicationInstance.setDiscount(rs.getInt("appDiscount"));
                    applicationInstance.setCibil(rs.getInt("appCibil"));
                    applicationInstance.setBankAcc(rs.getString("appBankAcc"));
                    applicationInstance.setRevenue(rs.getInt("appRevenue"));
                    applicationInstance.setEmpExpense(rs.getInt("appEmpExpense"));
                    applicationInstance.setTotalAmt(rs.getInt("appTotalAmt"));
                    applicationInstance.setInterest(rs.getInt("appInterest"));
                    applicationInstance.setStatus(rs.getString("appStatus"));
                    applicationInstance.setApplicationDate(rs.getTimestamp("appDate"));
 applicationInstance.setReason(rs.getString("appReason"));
    }
return applicationInstance;
}


// traverse application by all id 
public boolean deleteApplicationById(int aid) throws SQLException{
boolean delete = false;
//    create query
String query ="delete from  application where appId=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

pstmt.setInt(1, aid);

//execute query
pstmt.executeUpdate();
    
//set the boolean value to true if done 
delete=true;
        
return delete;
}

}



