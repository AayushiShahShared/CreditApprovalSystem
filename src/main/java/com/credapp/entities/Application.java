package com.credapp.entities;

import java.io.ObjectInputFilter;
import java.sql.Timestamp;
import jdk.jfr.Timespan;

public class Application {

// declaring fields of application form  to store to database 
    private int id;
    private String companyName;
    private int loanAmount;
    private int period;
    private int rate;
    private int discount;
    private int cibil;
    private String bankAcc;
    private int revenue;
    private int empExpense;
    private float totalAmt;
    private float interest;
    private String status;
    private Timestamp applicationDate;
    private String comment;
    private String reason;

//constructors
    public Application(String companyName, int loanAmount, int period, int discount, int cibil, String bankAcc, int revenue, int empExpense, float totalAmt, float interest, String status) {
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.discount = discount;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.totalAmt = totalAmt;
        this.interest = interest;
        this.status = status;
    }

    public Application(String companyName, int loanAmount, int period, int discount, int cibil, String bankAcc, int revenue, int empExpense, float totalAmt, float interest, String status, String reason) {
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.discount = discount;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.totalAmt = totalAmt;
        this.interest = interest;
        this.status = status;
        this.reason = reason;
    }

    public Application(String companyName, int loanAmount, int period, int rate, int cibil, String bankAcc, int revenue, float totalAmt, float interest, String status) {
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.rate = rate;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.totalAmt = totalAmt;
        this.interest = interest;
        this.status = status;
    }

    public Application(int id, String companyName, int loanAmount, int period, int rate, int cibil, String bankAcc, int revenue, int empExpense, float totalAmt, float interest, String status, Timestamp applicationDate) {
        this.id = id;
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.rate = rate;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.totalAmt = totalAmt;
        this.interest = interest;
        this.status = status;
        this.applicationDate = applicationDate;
    }

//    public Application(int id, String companyName, int loanAmount, int period, int discount, int cibil, String bankAcc, int revenue, int empExpense, float totalAmt, float interest, String status, Timestamp applicationDate, String comment,String reason) {
//        this.id = id;
//        this.companyName = companyName;
//        this.loanAmount = loanAmount;
//        this.period = period;
//        this.discount = discount;
//        this.cibil = cibil;
//        this.bankAcc = bankAcc;
//        this.revenue = revenue;
//        this.empExpense = empExpense;
//        this.totalAmt = totalAmt;
//        this.interest = interest;
//        this.status = status;
//        this.applicationDate = applicationDate;
//        this.comment = comment;
//        this.reason = reason;
//    }
    public Application(int id, String companyName, int loanAmount, int period, int discount, int cibil, String bankAcc, int revenue, int empExpense, float totalAmt, float interest, String status, Timestamp applicationDate, String comment, String reason) {
        this.id = id;
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.discount = discount;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.totalAmt = totalAmt;
        this.interest = interest;
        this.status = status;
        this.applicationDate = applicationDate;
        this.comment = comment;
        this.reason = reason;
    }

    public Application(String companyName, int loanAmount, int period, int rate, int cibil, String bankAcc, int revenue, int empExpense, String status) {
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.rate = rate;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.status = status;
    }

//    public Application(String companyName, int loanAmount, int period, int rate, int cibil, String bankAcc, int revenue, String status) {
//        this.companyName = companyName;
//        this.loanAmount = loanAmount;
//        this.period = period;
//        this.rate = rate;
//        this.cibil = cibil;
//        this.bankAcc = bankAcc;
//        this.revenue = revenue;
//        this.status = status;
//    }
    public Application() {
    }

    public Application(int id, String cName, int loan, int period, int rate, int cibil, String bankAcc, int revenue, int empExpense, String status, Timestamp appDate) {
        this.id = id;
        this.companyName = cName;
        this.loanAmount = loan;
        this.period = period;
        this.rate = rate;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.status = status;
        this.applicationDate = appDate;
    }

//    public Application(int id, String cName, int loan, int period, int rate, int cibil, String bankAcc, int revenue, int empExpense, String status, Timestamp appDate, String comment) {
//        this.id = id;
//        this.companyName = cName;
//        this.loanAmount = loan;
//        this.period = period;
//        this.rate = rate;
//        this.cibil = cibil;
//        this.bankAcc = bankAcc;
//        this.revenue = revenue;
//        this.empExpense = empExpense;
//        this.status = status;
//        this.applicationDate = appDate;
//        this.comment = comment;
//    }
    public Application(int id, String cName, int loan, int period, int discount, int cibil, String bankAcc, int revenue, int empExpense, String status, Timestamp appDate, String comment, String reason) {
        this.id = id;
        this.companyName = cName;
        this.loanAmount = loan;
        this.period = period;
        this.discount = discount;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.status = status;
        this.applicationDate = appDate;
        this.comment = comment;
        this.reason = reason;
    }

    public Application(String companyName, int loanAmount, int period, int discount, int cibil, String bankAcc, int revenue, int empExpense, String status, String reason) {
        this.companyName = companyName;
        this.loanAmount = loanAmount;
        this.period = period;
        this.discount = discount;
        this.cibil = cibil;
        this.bankAcc = bankAcc;
        this.revenue = revenue;
        this.empExpense = empExpense;
        this.status = status;
        this.reason = reason;
    }

//    getters and setters
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getCibil() {
        return cibil;
    }

    public void setCibil(int cibil) {
        this.cibil = cibil;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getEmpExpense() {
        return empExpense;
    }

    public void setEmpExpense(int empExpense) {
        this.empExpense = empExpense;
    }

    public float getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(float totalAmt) {
        this.totalAmt = totalAmt;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Timestamp applicationDate) {
        this.applicationDate = applicationDate;
    }

//    tostring overloading
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Application details : [ id=").append(id)
                .append(", company name=").append(companyName)
                .append(", loan amount=").append(loanAmount)
                .append(", period=").append(period)
                .append(", rate=").append(rate)
                .append(", cibil=").append(cibil)
                .append(", bank account=").append(bankAcc)
                .append(", revenue=").append(revenue)
                .append(", employee expense=").append(empExpense)
                .append(", total amount=").append(totalAmt)
                .append(", interest=").append(interest)
                .append(", status=").append(status)
                .append(", application date=").append(applicationDate)
                .append(", comment=").append(comment)
                .append(" ]");
        return builder.toString();
    }

}
