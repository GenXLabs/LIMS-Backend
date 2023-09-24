package com.kiu.lims.controller;

import com.kiu.lims.entity.EmailRequest;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EmailController {
    @PostMapping("/api/v1/lims/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) throws IOException {
        Email from = new Email(emailRequest.getFrom());
        String subject = emailRequest.getSubject();
        Email to = new Email(emailRequest.getTo());
        Content content = new Content("text/plain", emailRequest.getContent());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return "Email sent with status code: " + response.getStatusCode();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
