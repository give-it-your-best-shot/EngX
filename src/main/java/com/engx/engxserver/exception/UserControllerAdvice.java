package com.engx.engxserver.exception;

import com.engx.engxserver.controller.UserController;
import java.time.LocalDateTime;
import javax.security.auth.login.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerAdvice {
    @ExceptionHandler(LoginException.class)
    ResponseEntity<ApiExceptionResponse> handleRegistrationException(LoginException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
