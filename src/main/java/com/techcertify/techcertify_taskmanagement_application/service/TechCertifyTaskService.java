package com.techcertify.techcertify_taskmanagement_application.service;

import com.techcertify.techcertify_taskmanagement_application.data.model.Task;
import com.techcertify.techcertify_taskmanagement_application.data.model.User;
import com.techcertify.techcertify_taskmanagement_application.data.repository.TaskRepository;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.TaskRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.GetTaskResponse;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.TaskResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.TaskNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus.IN_PROGRESS;

@Service
@RequiredArgsConstructor
public class TechCertifyTaskService implements TaskService{

    private final TechCertifyUserService certifyUserService;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public TaskResponse createTask(TaskRequest request) throws UserNotFoundException {
      User foundUser = certifyUserService.findUserById(request.getUserId());
      Task task = Task.builder()
              .taskDescription(request.getTaskDescription())
              .dueDate(LocalDateTime.now().plusDays(request.getDueDate()))
              .UserTask(foundUser)
              .taskStatus(IN_PROGRESS)
              .build();
      var savedTask = taskRepository.save(task);
        return TaskResponse.builder()
              .id(savedTask.getId())
              .message("successfully created")
              .build();
    }

    @Override
    public GetTaskResponse getTask(Long taskId, Long userId) throws UserNotFoundException, TaskNotFoundException {
        Task foundTask = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("task not found"));
        boolean isValid = Objects.equals(foundTask.getUserTask().getId(), userId);
        if(!isValid){
            throw  new UserNotFoundException("user not found");
        }
        return modelMapper.map(foundTask, GetTaskResponse.class);
    }

    @Override
    public List<GetTaskResponse> getAllTask(Long userId) {
        List<Task> allTask = taskRepository.findAll();
       return allTask.stream()
                .filter(task -> task.getUserTask().getId() == userId)
                .map(task -> modelMapper.map(task, GetTaskResponse.class))
                .toList();
    }

    @Override
    public String deleteTask(Long taskId, Long userId) throws UserNotFoundException, TaskNotFoundException {
      GetTaskResponse foundTask =  getTask(taskId, userId);
      taskRepository.deleteById(foundTask.getId());
        return "successfully delete";
    }

    @Override
    public String deleteAllTask(Long userId) {
        List<Task> allTask = taskRepository.findAll();
        List<Task> userTasks =  allTask.stream()
                .filter(task -> task.getUserTask().getId() == userId)
                .toList();
        taskRepository.deleteAll(userTasks);
        return "successfully delete";
    }

    @Override
    public TaskResponse updateTask(TaskRequest request) throws UserNotFoundException, TaskNotFoundException {
        Task foundTask = taskRepository.findById(request.getTaskId()).orElseThrow(()-> new TaskNotFoundException("task not found"));
        boolean isValid = Objects.equals(foundTask.getUserTask().getId(), request.getUserId());
        if(!isValid){
            throw  new UserNotFoundException("user not found");
        }
           foundTask.setTaskStatus(request.getStatus());
          var savedTask =  taskRepository.save(foundTask);
        return TaskResponse.builder()
                  .id(savedTask.getId())
                  .message("successfully updated")
                  .build();
    }


}
