package com.techcertify.techcertify_taskmanagement_application.service.mailservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sender {
    private String name;
    private String email;

    public Sender(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
