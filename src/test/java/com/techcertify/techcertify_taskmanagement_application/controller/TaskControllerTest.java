package com.techcertify.techcertify_taskmanagement_application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.TaskRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testCreateTask() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlYnVraXp5MTJAZ21haWwuY29tIiwiaWF0IjoxNzAzODEyMTkwLCJleHAiOjE3MDM4OTg1OTB9.E9EONv7bLKm85W2JXt9A_pK-BOwgfpJ3tHfQLONxsP0";

        ObjectMapper mapper = new ObjectMapper();
        TaskRequest request = new TaskRequest();
        request.setTaskDescription("learn PYTHON in 10 days in ten ");
        request.setDueDate(10);
        try {
            mockMvc.perform(post("/api/v1/task")
                            .content(mapper.writeValueAsString(request))
                            .contentType(APPLICATION_JSON)
                            .header(AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGetUserTask() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlYnVraXp5MTJAZ21haWwuY29tIiwiaWF0IjoxNzAzODA5NjYwLCJleHAiOjE3MDM4MTExMDB9.AJIUYo1W1PVTOiREU9BT8_sTdS9kup9O3YWzTVtBVYk";
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/task/3")
                     .header(AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetAllTask(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlYnVraXp5MTJAZ21haWwuY29tIiwiaWF0IjoxNzAzODEyMTkwLCJleHAiOjE3MDM4OTg1OTB9.E9EONv7bLKm85W2JXt9A_pK-BOwgfpJ3tHfQLONxsP0";
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/task/1/getAllTask")
                    .header(AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print()).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTask(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlYnVraXp5MTJAZ21haWwuY29tIiwiaWF0IjoxNzAzODEyMTkwLCJleHAiOjE3MDM4OTg1OTB9.E9EONv7bLKm85W2JXt9A_pK-BOwgfpJ3tHfQLONxsP0";
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/task/3")
                     .header(AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print()).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void deleteAllTask(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlYnVraXp5MTJAZ21haWwuY29tIiwiaWF0IjoxNzAzODEyMTkwLCJleHAiOjE3MDM4OTg1OTB9.E9EONv7bLKm85W2JXt9A_pK-BOwgfpJ3tHfQLONxsP0";
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/task/1/deleteAllTask")
                     .header(AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print()).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateTask(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlYnVraXp5MTJAZ21haWwuY29tIiwiaWF0IjoxNzAzODEyMTkwLCJleHAiOjE3MDM4OTg1OTB9.E9EONv7bLKm85W2JXt9A_pK-BOwgfpJ3tHfQLONxsP0";
        ObjectMapper mapper = new ObjectMapper();
        TaskRequest request = new TaskRequest();
        request.setTaskId(5L);
        request.setStatus(TaskStatus.valueOf("COMPLETED"));
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/task/update")
                  .content(mapper.writeValueAsString(request))
                 .contentType(APPLICATION_JSON)
                  .header(AUTHORIZATION, "Bearer " + token))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print()).andReturn();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    }
