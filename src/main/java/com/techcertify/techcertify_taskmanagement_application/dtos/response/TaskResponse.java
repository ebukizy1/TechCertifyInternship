package com.techcertify.techcertify_taskmanagement_application.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {
    private String message;
    private Long id;
}
