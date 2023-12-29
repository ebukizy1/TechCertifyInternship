package com.techcertify.techcertify_taskmanagement_application.service;

import com.techcertify.techcertify_taskmanagement_application.dtos.request.RegisterUserRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.AuthenticateUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServicesTest {
   @Autowired
    private UserService userService;
    private RegisterUserRequest registerUserRequest;

    @BeforeEach
    void startWith(){
        registerUserRequest= RegisterUserRequest
                .builder()
                .username("ebukizy1")
                .email("ebukizy1@gmail.com")
                .password("ebukizy4u@")
                .build();
    }

    @Test
    public void RegisterUser(){
       AuthenticateUserResponse registerUserResponse = userService.register(registerUserRequest);
       assertThat(registerUserResponse).isNotNull();
       assertThat(registerUserResponse.getId()).isNotNull();
    }


}
