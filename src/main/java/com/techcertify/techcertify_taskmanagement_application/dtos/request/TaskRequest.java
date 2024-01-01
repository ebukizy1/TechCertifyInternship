package com.techcertify.techcertify_taskmanagement_application.dtos.request;

import com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    private Long userId;
    private String taskDescription;
    private Integer dueDate;
    private Long taskId;
    private TaskStatus status;

}
