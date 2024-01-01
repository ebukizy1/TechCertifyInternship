package com.techcertify.techcertify_taskmanagement_application.dtos.response;

import com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
public class GetTaskResponse {
    private Long id;
    private String taskDescription;
    private TaskStatus taskStatus;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime dueDate;

}
