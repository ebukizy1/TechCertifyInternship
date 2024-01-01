package com.techcertify.techcertify_taskmanagement_application.dtos.request;

import com.techcertify.techcertify_taskmanagement_application.data.model.Role;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
