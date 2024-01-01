package com.techcertify.techcertify_taskmanagement_application.service;

import com.techcertify.techcertify_taskmanagement_application.dtos.request.EmailRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.EmailResponse;
import com.techcertify.techcertify_taskmanagement_application.service.mailservice.MailService;
import com.techcertify.techcertify_taskmanagement_application.service.mailservice.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;


    @Test
    public void testSendMail(){
        Recipient recipient = new Recipient();
        recipient.setName("emmmmuel");
        recipient.setEmail("ebukizy1@gmail.com");
        EmailRequest emailRequest = new EmailRequest();
        List<Recipient> recipients = List.of(recipient);
        emailRequest.setRecipients(recipients);
        emailRequest.setSubject("Welcome to TechCertify Task Management Application");
        emailRequest.setHtmlContent("<html><head></head><body><p>Hello </p> it is a pleasure in using tech certify management application. </p></body></html>");
       EmailResponse response = mailService.sendMail(emailRequest);
       assertThat(response.getMessageId()).isNotNull();
       assertThat(response.getCode()).isNotNull();
       assertEquals(201, response.getCode());
    }

}
