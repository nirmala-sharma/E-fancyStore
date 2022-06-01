package com.example.demo.services;

import java.util.Properties;

import javax.mail.Session;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public  boolean sendEmail(String subject,String message,String to) {

        // Recipient's email ID needs to be mentioned.
        boolean f=false;

        // Sender's email ID needs to be mentioned
        String from = "dillisharma189@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("dillisharma189@gmail.com", "ilvhqmickhsggesw");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage m = new MimeMessage(session);

            // Set From: header field of the header.
            m.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            m.setSubject(subject);

            // Now set the actual message
            m.setText(message);

            System.out.println("sending...");
            // Send message
            Transport.send(m);
            System.out.println("Sent message successfully....");
        
        f=true;
        
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return f;
    }
}
