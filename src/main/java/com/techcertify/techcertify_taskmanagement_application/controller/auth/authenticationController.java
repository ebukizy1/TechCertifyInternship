package com.techcertify.techcertify_taskmanagement_application.controller.auth;

import com.techcertify.techcertify_taskmanagement_application.dtos.request.LoginRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.request.RegisterUserRequest;
import com.techcertify.techcertify_taskmanagement_application.dtos.response.AuthenticateUserResponse;
import com.techcertify.techcertify_taskmanagement_application.exception.EmailAlreadyExistException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class authenticationController {

    private final UserService userService;
    @PostMapping()
    public ResponseEntity<AuthenticateUserResponse> register(@RequestBody RegisterUserRequest request) throws EmailAlreadyExistException {
        return ResponseEntity.status(CREATED).body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateUserResponse> login(@RequestBody LoginRequest request) throws UserNotFoundException {
        AuthenticateUserResponse response = userService.authenticate(request);
        return ResponseEntity.ok(response);
    }





}
