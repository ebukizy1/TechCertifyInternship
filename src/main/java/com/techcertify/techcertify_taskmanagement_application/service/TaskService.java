package com.techcertify.techcertify_taskmanagement_application.service;

import com.techcertify.techcertify_taskmanagement_application.data.model.Task;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.TaskRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.GetTaskResponse;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.TaskResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.TaskNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;

import java.util.List;

public interface TaskService {


    TaskResponse createTask(TaskRequest request) throws UserNotFoundException;


    GetTaskResponse getTask(Long taskId, Long userId) throws UserNotFoundException, TaskNotFoundException;

    List<GetTaskResponse> getAllTask(Long userId);

    String deleteTask(Long taskId, Long userId) throws UserNotFoundException, TaskNotFoundException;

    String deleteAllTask(Long userId);

    TaskResponse updateTask(TaskRequest request) throws UserNotFoundException, TaskNotFoundException;
}
