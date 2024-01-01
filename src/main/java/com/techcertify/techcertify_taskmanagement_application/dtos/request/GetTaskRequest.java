package com.techcertify.techcertify_taskmanagement_application.dtos.request;

import com.techcertify.techcertify_taskmanagement_application.data.model.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTaskRequest {

    private Long userId;
    private Long taskId;
    private TaskStatus status;
}
