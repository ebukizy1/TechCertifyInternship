package com.techcertify.techcertify_taskmanagement_application.controller.taskController;

import com.techcertify.techcertify_taskmanagement_application.data.model.Task;
import com.techcertify.techcertify_taskmanagement_application.data.model.User;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.TaskRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.GetTaskResponse;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.TaskResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.TaskNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PrimitiveIterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @Operation(
            description = "this is a create task endpoint it is your choice to pass the due date but implicitly it is set to 7 days" ,
            summary = "you dont have to pass the userId explicit to create task all you need is the token the server is already doing the extraction of the userID",
            responses = {
                    @ApiResponse(
                            description = "successfully created",
                            responseCode = "201"
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request, Authentication authentication) throws UserNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        request.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }
    @Operation(
            description = "this is a get task endpoint you need to the userId but you dont have to pass it",
            summary = "you dont have to pass the userId explicit to get task, all you need is the token the server is already doing the extraction of the userID",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/{taskId}")
    public ResponseEntity <GetTaskResponse> getTask(@PathVariable Long taskId, Authentication authentication) throws UserNotFoundException, TaskNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.getTask(taskId, userId));
    }

    @Operation(
            description = "this is a get all task endpoint you need to the userId but you dont have to pass it",
            summary = "you dont have to pass the userId explicit to get task all the task you need is the token the server is already doing the extraction of the userID",
            responses = {
                    @ApiResponse(
                            responseCode = "200"
                    )
            }
    )
    @GetMapping("/{userId}/getAllTask")
    public ResponseEntity <List<GetTaskResponse>> getAllTask(Authentication authentication){
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.getAllTask(userId));
    }

    @Operation(
            description = "this is a delete task endpoint you need to the userId but you dont have to pass it",
            summary = "you dont have to pass the userId explicit to get delete the task you need is the token the server is already doing the extraction of the userID"

    )
    @DeleteMapping("/{taskId}")
    public ResponseEntity <String> deleteTask(@PathVariable("taskId") Long taskId,  Authentication authentication) throws UserNotFoundException, TaskNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.deleteTask(taskId, userId));
    }

    @Operation(
            description = "this is a all delete task endpoint you need to the userId but you dont have to pass it",
            summary = "you dont have to pass the userId explicit to get delete all the task you need is the token the server is already doing the extraction of the userID"

    )

    @DeleteMapping("/{userId}/deleteAllTask")
    public ResponseEntity <String> deleteAllTask(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.deleteAllTask(userId));
    }

    @Operation(
            description = "this is a update task endpoint you need to the userId but you dont have to pass it",
            summary = "you dont have to pass the userId explicit to update the task you need is the token the server is already doing the extraction of the userID"

    )
    @PutMapping("/update")
    public ResponseEntity <TaskResponse> updateTask(@RequestBody TaskRequest request,  Authentication authentication) throws UserNotFoundException, TaskNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        request.setUserId(userId);
        return ResponseEntity.ok().body(taskService.updateTask(request));
    }






}
