package com.example.movie.controller;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailSenderService {
	
	public void sendEmail(String toEmail,String body, String subject) throws MessagingException
	{
			
		String senderEmail = "popcornpicks.verify@gmail.com";
        String senderPassword = "wwal excx kjhp ypxv"; // Your email password
        //String senderPassword = "Skans@b4"; // Your email password

        
        // SMTP server properties (in this example, we're using Gmail's SMTP server)
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 465;// Port for TLS

        // Create JavaMail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.quitwait", "true");

        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        // Create a session with the email server
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        
        try {
            // Create a new email message
            Message message = new MimeMessage(session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);
            
            
            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
//	private JavaMailSender javaMailSender = new JavaMailSenderImpl();
//	public void sendEmail(String toEmail,String body, String subject) throws MessagingException, jakarta.mail.MessagingException
//	{
//		
//		
//		MimeMessage mimeMessage = new MimeMessage();
//		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//		mimeMessageHelper.setFrom("popcornpicks.verify@gmail.com");
//		mimeMessageHelper.setTo(toEmail);
//		mimeMessageHelper.setText(body);
//		mimeMessageHelper.setSubject(subject);
//	
//		javaMailSender.send(mimeMessage);
//		System.out.println("Mail sent");
//	}
}
