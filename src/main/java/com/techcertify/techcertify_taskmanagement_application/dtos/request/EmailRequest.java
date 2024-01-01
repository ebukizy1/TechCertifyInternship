package com.techcertify.techcertify_taskmanagement_application.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techcertify.techcertify_taskmanagement_application.service.mailservice.Recipient;
import com.techcertify.techcertify_taskmanagement_application.service.mailservice.Sender;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EmailRequest {
    private Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;
    private String subject;
    private String htmlContent;
    public EmailRequest(){
        this.sender = new Sender( "TechCertify.inc", "TechCertify@hotmail.inc");
    }


}
