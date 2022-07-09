package com.credapp.entities;


import java.sql.Timestamp;

public class Company {

//    data members
    
private int cId;
private String cName;
private int cRNo;
private String cStatus;
private String cCategory;
private String cClass;
private int cAge;
private int cSize;    
private String cOfficeLocs;
private String cEmail;
private String cPassword;
private String cAbout;
private Timestamp cRegDateTime;
private String code;
private String verify;
private String cType;
private String isEnable;

//Constructors

  public Company(String cName, int cRNo, String cStatus, String cCategory, String cClass, int cAge, int cSize, String cOfficeLocs, String cEmail, String cPassword, String cAbout, String code) {
        this.cName = cName;
        this.cRNo = cRNo;
        this.cStatus = cStatus;
        this.cCategory = cCategory;
        this.cClass = cClass;
        this.cAge = cAge;
        this.cSize = cSize;
        this.cOfficeLocs = cOfficeLocs;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cAbout = cAbout;
        this.code = code;
    }

    public Company(int cId, String cName, int cRNo, String cStatus, String cCategory, String cClass, int cAge, int cSize, String cOfficeLocs, String cEmail, String cPassword, String cAbout, Timestamp cRegDateTime) {
        this.cId = cId;
        this.cName = cName;
        this.cRNo = cRNo;
        this.cStatus = cStatus;
        this.cCategory = cCategory;
        this.cClass = cClass;
        this.cAge = cAge;
        this.cSize = cSize;
        this.cOfficeLocs = cOfficeLocs;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cAbout = cAbout;
        this.cRegDateTime = cRegDateTime;
    }

    public Company(String cName, String cStatus, String cCategory, String cClass, int cAge, int cSize, String cOfficeLocs, String cEmail, String cPassword, String cAbout) {
        this.cName = cName;
        this.cStatus = cStatus;
        this.cCategory = cCategory;
        this.cClass = cClass;
        this.cAge = cAge;
        this.cSize = cSize;
        this.cOfficeLocs = cOfficeLocs;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cAbout = cAbout;
    }

    public Company() {
    }

    public Company(String cName, int cRNo, String cStatus, String cCategory, String cClass, int cAge, int cSize, String cOfficeLocs, String cEmail, String cPassword, String cAbout) {
        this.cName = cName;
        this.cRNo = cRNo;
        this.cStatus = cStatus;
        this.cCategory = cCategory;
        this.cClass = cClass;
        this.cAge = cAge;
        this.cSize = cSize;
        this.cOfficeLocs = cOfficeLocs;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cAbout = cAbout;
       
    }

//Getters and Setters

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    
    
    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    
    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcRNo() {
        return cRNo;
    }

    public void setcRNo(int cRNo) {
        this.cRNo = cRNo;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getcCategory() {
        return cCategory;
    }

    public void setcCategory(String cCategory) {
        this.cCategory = cCategory;
    }

    public String getcClass() {
        return cClass;
    }

    public void setcClass(String cClass) {
        this.cClass = cClass;
    }

    public int getcAge() {
        return cAge;
    }

    public void setcAge(int cAge) {
        this.cAge = cAge;
    }

    public int getcSize() {
        return cSize;
    }

    public void setcSize(int cSize) {
        this.cSize = cSize;
    }

    public String getcOfficeLocs() {
        return cOfficeLocs;
    }

    public void setcOfficeLocs(String cOfficeLocs) {
        this.cOfficeLocs = cOfficeLocs;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getcAbout() {
        return cAbout;
    }

    public void setcAbout(String cAbout) {
        this.cAbout = cAbout;
    }

    public Timestamp getcRegDateTime() {
        return cRegDateTime;
    }

    public void setcRegDateTime(Timestamp cRegDateTime) {
        this.cRegDateTime = cRegDateTime;
    }

    
    
//    tostring overloading
    @Override
    public String toString(){
        StringBuilder builder =new StringBuilder(); 
        builder.append("Company details : [ id=").append(cId)
                .append(", company name=").append(cName)
                .append(", comapny registration number=").append(cRNo)
                .append(", status=").append(cStatus)
                .append(", category=").append(cCategory)
                .append(", class=").append(cClass)
                .append(", age=").append(cAge)
                .append(", size=").append(cSize)
                .append(", office locations=").append(cOfficeLocs)
                .append(", email=").append(cEmail)
                .append(", about company=").append(cAbout)
                .append(", status=").append(cStatus)
                .append(", date=").append(cRegDateTime)
                .append(", verify=").append(verify)
                .append(", type=").append(cType)
                .append(", isEnable=").append(isEnable)
                .append(" ]");
        

 return builder.toString();
    }

    
}
