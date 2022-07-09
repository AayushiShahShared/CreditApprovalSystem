/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credapp.conf;

import com.credapp.helper.ConnectionProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

/**
 *
 * @author Aayushi
 */
public class loadPropertyFile {
    
 public static Properties getDataFromProperties(String filename){

    
    
    Properties properties = new Properties();   
        FileInputStream fis ;  

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
    
    
}
