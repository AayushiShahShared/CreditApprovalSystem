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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aayushi
 */
public class PendingApplicationDao {
    
    private final Connection con;

    public PendingApplicationDao(Connection con) {
        this.con = con;
    }
    
    
//    submit form with pending state so use this method
public boolean submitPendingForm(Application application){
    boolean submit=false;
    try {
//       create query  
        String query = "insert into pendingapplication(appId,appCompanyName,appLoanAmount,appPeriod,appDiscount,appCibil,appBankAcc,appRevenue,appEmpExpense,appTotalAmt,appInterest,appStatus,appDate,appReason) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
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

// traverse all applications with pending state
public int getPendingApplicationsCount() throws SQLException{

    int count=0;
//    create query
String query ="SELECT * FROM pendingapplication";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
ResultSet rs =pstmt.executeQuery();
while(rs.next()){
    count++;
}


return count;
}



// traverse all applications
public ArrayList<Application> getAllPendingApplicationsFromRange(int start, int recordCount) {

//    create an application list
    ArrayList<Application> applicationList = new ArrayList<>();

    
//    create query
String query ="SELECT * FROM pendingapplication LIMIT ?,?";

    
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


// traverse all applications with pending state
public ResultSet getAllPendingApplications() throws SQLException{

//    create query
String query ="SELECT * FROM pendingapplication";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

//execute query
ResultSet rs =pstmt.executeQuery();

return rs;
}








// traverse all applications with pending state
public ResultSet getAllPendingApplicationsByName(String companyName) throws SQLException{

//    create query
String query ="SELECT * FROM pendingapplication WHERE appCompanyName=?";

//create prepared statement
PreparedStatement pstmt = con.prepareStatement(query);

pstmt.setString(1, companyName);
//execute query
ResultSet rs =pstmt.executeQuery();

return rs;
}

// traverse all applications with pending state
public Application getPendingApplicationById(int id){

    
//        create application object
        Application applicationInstance = new Application();
        
//    create query
String query ="SELECT * FROM pendingapplication WHERE appId=?";

//create prepared statement
PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(query);
pstmt.setInt(1, id);

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
            Logger.getLogger(PendingApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

return applicationInstance;

}






//delete application
public boolean deletePendingApplication(int id){
    boolean delete = false;
    try {
//          create query 
//DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
        String query = "delete from  pendingapplication where appId=?";
        
//        create statement
        PreparedStatement pstmt =con.prepareStatement(query);
        
//        set the values to prepared statement
pstmt.setInt(1, id);

//execute the query
pstmt.executeUpdate();

//set the boolean value to true if done 
delete=true;
        
    } catch (Exception e) {
    }
    return delete;
    }


}
