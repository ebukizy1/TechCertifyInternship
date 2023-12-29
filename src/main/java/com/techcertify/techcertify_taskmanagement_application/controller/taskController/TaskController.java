package com.techcertify.techcertify_taskmanagement_application.controller.taskController;

import com.techcertify.techcertify_taskmanagement_application.data.model.Task;
import com.techcertify.techcertify_taskmanagement_application.data.model.User;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.TaskRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.GetTaskResponse;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.TaskResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.TaskNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.service.TaskService;
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
    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request, Authentication authentication) throws UserNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        request.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity <GetTaskResponse> getTask(@PathVariable Long taskId, Authentication authentication) throws UserNotFoundException, TaskNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.getTask(taskId, userId));
    }

    @GetMapping("/{userId}/getAllTask")
    public ResponseEntity <List<GetTaskResponse>> getAllTask(Authentication authentication){
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.getAllTask(userId));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity <String> deleteTask(@PathVariable("taskId") Long taskId,  Authentication authentication) throws UserNotFoundException, TaskNotFoundException {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.deleteTask(taskId, userId));
    }

    @DeleteMapping("/{userId}/deleteAllTask")
    public ResponseEntity <String> deleteAllTask(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof User user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = user.getId();
        return ResponseEntity.ok().body(taskService.deleteAllTask(userId));
    }

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
