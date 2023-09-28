package com.kiu.lims.controller;

import com.kiu.lims.entity.EmailRequest;
import com.kiu.lims.model.ResponseModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
@CrossOrigin
public class EmailController {

    @PostMapping("/api/v1/lims/email/send-email")
    public ResponseModel sendEmail(@RequestBody EmailRequest emailRequest) {
        // Email credentials
        final String username = "kiu.lims.notifications@pixelcore.lk";
        final String password = "LIMS@KIU_PC2023";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.pixelcore.lk"); // Outlook SMTP server
        props.put("mail.smtp.port", "587"); // Outlook SMTP port

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        ResponseModel response = null;
        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getRecipient()));
            message.setSubject(emailRequest.getSubject());
            message.setContent(emailRequest.getContent(), "text/html");

            // Send the email
            Transport.send(message);

            // Create a success response
            response = new ResponseModel();
            response.setCode(200); // Set your desired HTTP status code
            response.setMessage("Email sent successfully");
            response.setData(null); // You can set data if needed

            return response;

        } catch (MessagingException e) {
            ResponseModel errorResponse = new ResponseModel();
            errorResponse.setCode(500); // Set an appropriate error status code
            errorResponse.setMessage("Failed to send email: " + e.getMessage());
            errorResponse.setData(null);

            return response;
        }
    }
}
