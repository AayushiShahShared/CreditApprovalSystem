package com.credapp.services;

import com.credapp.entities.MailMessage;
import com.sun.mail.handlers.message_rfc822;
import java.util.Properties;
import java.util.Random;
import sun.tools.tree.ThisExpression;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    public MailService() {
    }
    
    public String getRandom(){
    Random random = new Random();
    int number = random.nextInt(999999);
    return String.format("%06d", number);
    }
    
   public static boolean sendEmail(MailMessage mailMessage){
    
       
//        variable for email status
boolean sent =false;

//            String cEmailFrom = "gajendragoswamics18@acropolis.in";
//            final String EmailFrom = "varshadshah161974@gmail.com";
//            final String password="Varshadshah@161974";
            final String EmailFrom = mailMessage.getFrom();
            final String password = mailMessage.getPassword();
            String subject = mailMessage.getSubject();
            String EmailTo = mailMessage.getTo();
            String message = mailMessage.getMessage();
            
                    //       variable for gmail host
//        String host = "smtp.gmail.com";

//get the system properties
Properties properties = System.getProperties();

//Properties properties = new Properties();
//        System.out.println("properties "+properties);


//       setting important information to properties object
  properties.put("mail.smtp.host", "localhost");
        properties.setProperty("mail.smtp.port", "465");
//        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.user", EmailFrom);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.socketFactory.port", "587");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.trust", "*");



//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.smtp.port", "465");
//        properties.setProperty("mail.smtp.port", "587");
//        properties.setProperty("mail.smtp.ssl.enable", "true");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.socketFactory.port", "587");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

//get the mail session object
  Session mSession = Session.getInstance(properties, new Authenticator() {
//
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailFrom, password);
//                return new PasswordAuthentication("varshadshah161974@gmail.com", "Varshadshah@161974");
//                return new PasswordAuthentication("gajendragoswamics18@acropolis.in", "acro@1234");

            }
        });



//Session mSession = Session.getInstance(properties, new Authenticator() {
//            
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(EmailFrom, password);
////                return new PasswordAuthentication("varshadshah161974@gmail.com", "Varshadshah@161974");
////                return new PasswordAuthentication("gajendragoswamics18@acropolis.in", "acro@1234");
//            
//            }
//        });
//        System.out.println("msessio start");
        mSession.setDebug(true);
//        System.out.println("msession end");
//        
//// step 2: compose the message

  MimeMessage mimeMessage = new MimeMessage(mSession);
        try {
            //setting the email fields in mail session

mimeMessage.setFrom(new InternetAddress(EmailFrom));
//mimeMessage.setFrom(new InternetAddress("credappdemo@gmail.com"));
//            mimeMessage.setFrom("gajendragoswamics18@acropolis.in");
//
////    adding the recipient to message
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(EmailTo));
//
////            adding subject to message
            mimeMessage.setSubject(subject);
//
////            adding the text content to message
            mimeMessage.setText(message);

//            out.println(mimeMessage.getFrom());
//            out.println(mimeMessage.getSubject());
//            out.println(mimeMessage.getContent());
//            out.println(mimeMessage.getRecipients(Message.RecipientType.TO));

//    step 3: send the message using transport class
Transport transport = mSession.getTransport("smtp");
//out.println("okkk");
//String mfrom = "yourGmailUsernameWithout@"// example laabidiraissi 
transport.connect("smtp.gmail.com", EmailFrom, EmailTo);
//out.println("okkk");

transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
//            out.println("okkk");

//Transport.send(mimeMessage);
//
            sent = true;

            
//    setting the from
//            mimeMessage.setFrom(mailMessage.getFrom());
////
//////    adding the recipient to message
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailMessage.getTo()));
////
//////            adding subject to message
//            mimeMessage.setSubject(mailMessage.getSubject());
////
//////            adding the text content to message
//            mimeMessage.setText(mailMessage.getMessage());
////
////            step 3: send the message using transport class
//            Transport.send(mimeMessage);
//Transport.send(mimeMessage);
//            sent =true;
//            System.out.println("done");        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sent;
                }

}


































//   public static boolean sendEmail(MailMessage mailMessage){
//    
//       
////        variable for email status
//boolean sent =false;
//                    //       variable for gmail host
//        String host = "smtp.gmail.com";
//
////get the system properties
//        Properties properties = System.getProperties();
//        System.out.println("properties "+properties);
//
////       setting important information to properties object
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
////get the mail session object
//        Session mSession = Session.getInstance(properties, new Authenticator() {
//            
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
////                return new PasswordAuthentication("varshadshah161974@gmail.com", "Varsha@74Pass");
//                return new PasswordAuthentication("gajendragoswamics18@acropolis.in", "acro@1234");
//            
//            }
//        });
////        System.out.println("msessio start");
//        mSession.setDebug(true);
////        System.out.println("msession end");
//        
//// step 2: compose the message
//        MimeMessage mimeMessage = new MimeMessage(mSession);
//        
//        try {
//            //setting the email fields in mail session
//
////    setting the from
//            mimeMessage.setFrom(mailMessage.getFrom());
//
////    adding the recipient to message
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailMessage.getTo()));
//
////            adding subject to message
//            mimeMessage.setSubject(mailMessage.getSubject());
//
////            adding the text content to message
//            mimeMessage.setText(mailMessage.getMessage());
//
////            step 3: send the message using transport class
//            Transport.send(mimeMessage);
// 
//            sent =true;
////            System.out.println("done");        
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sent;
//                }
////           
//    public boolean sendMail() {
//
////        variable for email status
//boolean sent =false;
////       variable for gmail host
//        String host = "smtp.gmail.com";
//
////get the system properties
//        Properties properties = System.getProperties();
//        System.out.println(properties);
//
////       setting important information to properties object
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth", "true");
//
////get the mail session object
//        Session mSession = Session.getInstance(properties, new Authenticator() {
//            
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("credapp123@gmail.com", "asdfGhjkl@456");
//            }
//            
//        });
//        
//        mSession.setDebug(true);
//
//// step 2: compose the message
//        MimeMessage mimeMessage = new MimeMessage(mSession);
//        
//        try {
//            //setting the email fields in mail session
//
////    setting the from
//            mimeMessage.setFrom(mailFrom);
//
////    adding the recipient to message
//            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
//
////            adding subject to message
//            mimeMessage.setSubject(mailSubject);
//
////            adding the text content to message
//            mimeMessage.setText(mailMessage);
//
////            step 3: send the message using transport class
//            Transport.send(mimeMessage);
// 
//            sent=true;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return sent;
//    }

