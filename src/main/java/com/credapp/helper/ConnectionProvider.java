
package com.credapp.helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class ConnectionProvider {
    
   private static Connection con;
   
private Properties getCredentialsFromPropertiesFile(String filename){

    
    
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); 
 encryptor.setPassword(System.getenv("JASYPT_ENCRYPTOR_PASSWORD"));

    Properties properties = new EncryptableProperties(encryptor);   
        FileInputStream fis;  

    try {
        fis = new FileInputStream("D:\\Libraries\\Documents\\internship\\project\\24 may credapp\\CredApp\\src\\main\\resources\\"+filename);
   
        if (fis == null) {
         
//            implement logger
            System.out.println("Sorry, unable to find :"+filename);
                
            }
        else{
        properties.load(fis);
        }
       } catch (IOException ex) {
           Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
       }
return properties;
}
   public static Connection getConnection(){
       try
    {
        if(con == null ){
        //load driver class
        Class.forName("com.mysql.cj.jdbc.Driver");
        
//get object of connection provider
ConnectionProvider connectionProvider = new ConnectionProvider();

//        get properties
Properties properties=connectionProvider.getCredentialsFromPropertiesFile("database.properties");

//        get username and password
//properties.getProperty("user");
//properties.getProperty("password");

     //create connection to database
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", properties);
//     con = DriverManager.getConnection("jdbc:mysql://192.168.25.128:30000/newcredapp", "root", "password");
    
        
        }
        }
    catch(ClassNotFoundException | SQLException e)
    {
       
    }
        return con;
    }

    
}




























//https://www.devglan.com/online-tools/jasypt-online-encryption-decryption