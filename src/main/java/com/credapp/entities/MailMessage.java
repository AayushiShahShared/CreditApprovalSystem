package com.credapp.entities;


public class MailMessage {
    private String message;
    private String to;
//    private String from = "varshadshah161974@gmail.com";
    private String from = "credappdemo@gmail.com";
    private String subject;
//    private String password  = "Varshadshah@161974";
//    private String password  = "Credappdemo@123";
    private String password  = "mjklfnxkwybjbadk";

    
    public MailMessage(String message, String subject,String to, String from ) {
        this.message = message;
        this.to = to;
        this.from = from;
        this.subject = subject;
    }

    public MailMessage() {
    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

   

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

//    public void setFrom(String from) {
//        this.from = from;
//    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
    
//    tostring overloading
    @Override
    public String toString(){
        StringBuilder builder =new StringBuilder(); 
        builder.append("Mail message : [ message=").append(message)
                .append(", mail sent to=").append(to)
                .append(", mail subject=").append(subject)
                .append(" ]"); 
 return builder.toString();
    }

    
}
