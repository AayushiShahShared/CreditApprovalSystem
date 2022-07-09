
package com.mycompany.mymail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class demo {
    public static void main(String[] args) {
        System.out.println("preparing");
        String msg="hey from credapp";
        String subject="credapp mail";
        String to = "dshah742000@gmail.com";
        String from="gajendragoswamics18@acropolis.in";
        
        sendEmail(msg,subject,to,from);
    }
    
    private static void sendEmail(String msg, String subject, String to, String from){
    
                    //       variable for gmail host
        String host = "smtp.gmail.com";

//get the system properties
        Properties properties = System.getProperties();
        System.out.println("properties "+properties);

//       setting important information to properties object
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

//get the mail session object
        Session mSession = Session.getInstance(properties, new Authenticator() {
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gajendragoswamics18@acropolis.in", "acro@1234");
            
            }
        });
        System.out.println("msessio start");
        mSession.setDebug(true);
        System.out.println("msession end");
        
// step 2: compose the message
        MimeMessage mimeMessage = new MimeMessage(mSession);
        
        try {
            //setting the email fields in mail session

//    setting the from
            mimeMessage.setFrom(from);

//    adding the recipient to message
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//            adding subject to message
            mimeMessage.setSubject(subject);

//            adding the text content to message
            mimeMessage.setText(msg);

//            step 3: send the message using transport class
            Transport.send(mimeMessage);
 
            System.out.println("done");        
        } catch (Exception e) {
            e.printStackTrace();
        }
                }
           
        
        }
    
    

