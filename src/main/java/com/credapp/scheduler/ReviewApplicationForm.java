package  com.credapp.scheduler;

import com.credapp.dao.ApplicationDao;
import com.credapp.dao.PendingApplicationDao;
import com.credapp.entities.Application;
import com.credapp.helper.ConnectionProvider;
import com.mysql.cj.protocol.Resultset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReviewApplicationForm implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
     float interest, totalAmt;   
           String cName, status, bankAcc ;
            int id, loan , period , rate , cibil , revenue ,empExpense ;
            
            Timestamp appDate ;
     
Application application ;

   System.out.println(new Date());
        
//        create application dao object
//PendingApplicationDao pdao = new PendingApplicationDao(ConnectionProvider.getConnection());
//ApplicationDao adao = new ApplicationDao(ConnectionProvider.getConnection());

//try {
            //call method to get the applications with pending state
//            ResultSet rs = pdao.getAllPendingApplications();
//        while(rs.next()){
//            System.out.println(rs.getInt("appId"));
//            System.out.println(rs.getString("appCompanyName"));
//            System.out.println(rs.getInt("appLoanAmount"));
//            System.out.println(rs.getInt("appPeriod"));
//            System.out.println(rs.getInt("appRate"));
//            System.out.println(rs.getInt("appCibil"));
//            System.out.println(rs.getString("appBankAcc"));
//            System.out.println(rs.getInt("appRevenue"));
//            System.out.println(rs.getInt("appEmpExpense"));
//            System.out.println(rs.getString("appStatus"));
//            System.out.println(rs.getTimestamp("appDate"));
//            
//            
//            id=rs.getInt("appId");
//           cName = rs.getString("appCompanyName");
//           loan =rs.getInt("appLoanAmount");
//           period =rs.getInt("appPeriod");
//            rate  = rs.getInt("appRate");
//            cibil = rs.getInt("appCibil");
//           bankAcc = rs.getString("appBankAcc");
//           revenue = rs.getInt("appRevenue");
//            empExpense = rs.getInt("appEmpExpense");
//            status = rs.getString("appStatus");
//            appDate = rs.getTimestamp("appDate");
//            
//            if(cibil > 100){
////      calculating the interest
//       interest=(loan*period*rate)/100;
//       
////   calculating the total amount
//    totalAmt=interest+loan;
//    
//                status = "Approved";
//            
//            application = new Application(id,cName,loan,period,rate,cibil,bankAcc,revenue,empExpense,totalAmt,interest,status,appDate);
//
//            adao.submitApprovedForm(application);
//            
//            }
//            else{
//            status ="Rejected";
//                      application = new Application(id,cName,loan,period,rate,cibil,bankAcc,revenue,empExpense,status,appDate);
//
//                        adao.submitRejectedForm(application);
//            }
//
//            pdao.deletePendingApplication(id);
//            System.out.println(status);
//      
            
//            application.setId(rs.getInt("appId"));
//application.setCompanyName(rs.getString("appCompanyName"));
//application.setLoanAmount(rs.getInt("appLoanAmount"));
//application.setPeriod(rs.getInt("appPeriod"));
//application.setRate(rs.getInt("appRate"));
//application.setCibil(rs.getInt("appCibil"));
//application.setBankAcc(rs.getString("appBankAcc"));
//application.setRevenue(rs.getInt("appRevenue"));
//application.setEmpExpense(rs.getInt("appEmpExpense"));
//application.setStatus(rs.getString("appStatus"));
//application.setApplicationDate(rs.getTimestamp("appDate"));

//if(100 <= rs.getInt("appCibil")){

    //   calculating the interest
//       interest=(application.getLoanAmount()*application.getPeriod()*application.getRate())/100;
       
//   calculating the total amount
//    totalAmt=interest+application.getLoanAmount();
    
//    setting approved values
//application.setTotalAmt(totalAmt);
//application.setInterest(interest);
//application.setStatus("Approved");
//
//    System.out.println(application.getStatus());
////update in db the updated approved values
//adao.updateApprovedForm(totalAmt, interest, application.getStatus());
//}
//else{
//application.setStatus("Rejected");
//    System.out.println(application.getStatus());
//
//adao.updateRejectedForm(application.getStatus());
//}


//        }
//        } catch (SQLException ex) {
//            Logger.getLogger(ReviewApplicationForm.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
    
    
}
