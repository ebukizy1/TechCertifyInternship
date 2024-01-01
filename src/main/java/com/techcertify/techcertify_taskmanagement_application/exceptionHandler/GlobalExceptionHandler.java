package com.techcertify.techcertify_taskmanagement_application.exceptionHandler;

import com.techcertify.techcertify_taskmanagement_application.exception.EmailAlreadyExistException;
import com.techcertify.techcertify_taskmanagement_application.exception.TaskNotFoundException;
import com.techcertify.techcertify_taskmanagement_application.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleTaskNotFoundException(TaskNotFoundException exception) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(Collections.singletonMap("error", exception.getMessage()))
                .status(HttpStatus.NOT_FOUND)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleUserNotException(UserNotFoundException exception) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(Collections.singletonMap("error", exception.getMessage()))
                .status(HttpStatus.NOT_FOUND)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleDuplicateEmailException(EmailAlreadyExistException exception) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(Collections.singletonMap("error", exception.getMessage()))
                .status(HttpStatus.BAD_REQUEST)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
