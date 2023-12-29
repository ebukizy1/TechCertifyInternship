package com.techcertify.techcertify_taskmanagement_application.service;

import com.techcertify.techcertify_taskmanagement_application.data.model.Task;
import com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.TaskRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.GetTaskResponse;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.TaskResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.TaskNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;


    @Test
    @Sql("/db/insert.sql")
    public void testUserCanCreateTask() throws UserNotFoundException {
        TaskRequest request = new TaskRequest();
        request.setUserId(101L);
        request.setTaskDescription("Read all chapter 4 to 10 in java textbook");
        request.setDueDate(10);
        TaskResponse taskResponse =  taskService.createTask(request);
        assertThat(taskResponse).isNotNull();
        assertThat(taskResponse.getId()).isNotNull();
    }
    @Test
    @Sql("/db/insert.sql")
    public void testUserCanGetTask() throws UserNotFoundException, TaskNotFoundException {
        GetTaskResponse taskResponse = taskService.getTask( 2L,101L);
        assertThat(taskResponse).isNotNull();
    }

    @Test
    @Sql("/db/insert.sql")
    public void testUserCanAllTask() {
        List<GetTaskResponse> taskList= taskService.getAllTask(101L);
        assertThat(taskList).isNotNull();
    }

    @Test
    @Sql("/db/insert.sql")
    public void testUserCanDeleteTask() throws UserNotFoundException, TaskNotFoundException {
        String message = taskService.deleteTask(3L, 102L);
        assertEquals("successfully delete", message);
    }
    @Test
    @Sql("/db/insert.sql")
    public void testUserCanDeleteAll(){
        String message = taskService.deleteAllTask(102L);
        assertEquals("successfully delete", message);
    }
    @Test
    @Sql("/db/insert.sql")
    public void testTaskCanBeUpdated() throws UserNotFoundException, TaskNotFoundException {
        TaskRequest request = new TaskRequest();
        request.setUserId(102L);
        request.setTaskId(3L);
        request.setStatus(TaskStatus.valueOf("COMPLETED"));
        TaskResponse response = taskService.updateTask(request);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getMessage()).isNotNull();
        assertEquals("successfully updated", response.getMessage());
    }








}
