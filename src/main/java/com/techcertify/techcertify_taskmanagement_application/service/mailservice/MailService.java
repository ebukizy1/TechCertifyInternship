package com.techcertify.techcertify_taskmanagement_application.service.mailservice;

import com.techcertify.techcertify_taskmanagement_application.dtos.request.EmailRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.EmailResponse;

public interface MailService {


    EmailResponse sendMail(EmailRequest emailRequest);
}