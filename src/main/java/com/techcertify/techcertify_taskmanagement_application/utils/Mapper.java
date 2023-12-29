package com.techcertify.techcertify_taskmanagement_application.utils;

import com.techcertify.techcertify_taskmanagement_application.data.model.User;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.EmailRequest;
import com.techcertify.techcertify_taskmanagement_application.service.mailservice.Recipient;

import java.util.List;

public class Mapper {
    public static EmailRequest getEmailRequest(User user) {
        EmailRequest emailRequest = new EmailRequest();
        Recipient recipient = new Recipient();
        recipient.setName(user.getUsername());
        recipient.setEmail(user.getEmail());
        emailRequest.setRecipients(List.of(recipient));
        emailRequest.setSubject("Welcome to TechCertify Task Management Application");
        emailRequest.setHtmlContent("<html><head></head><body><p>Hello </p> it is a pleasure in using tech certify management application. </p></body></html>");
        return emailRequest;
    }
}
