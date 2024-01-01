package com.techcertify.techcertify_taskmanagement_application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcertify.techcertify_taskmanagement_application.data.model.Role;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.LoginRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.RegisterUserRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterEndPoint() {
        ObjectMapper mapper = new ObjectMapper();
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("emmauel");
        request.setEmail("ebukizy12@gmail.com");
        request.setRole(Role.valueOf("USER"));
        request.setPassword("ebukizy1");
        try {
            mockMvc.perform(post("/api/v1/auth")
                   .content(mapper.writeValueAsString(request))
                   .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().is2xxSuccessful())
                   .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginEndPoint(){
        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("ebukizy12@gmail.com");

        loginRequest.setPassword("ebukizy1");
        try{
            mockMvc.perform(post("/api/v1/auth/login")
                    .content(mapper.writeValueAsString(loginRequest))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
