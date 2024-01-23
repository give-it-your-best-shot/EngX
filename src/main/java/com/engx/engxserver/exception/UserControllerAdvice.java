package com.engx.engxserver.exception;

import com.engx.engxserver.controller.UserController;
import com.engx.engxserver.dto.ResponseFailure;
import java.time.LocalDateTime;
import javax.security.auth.login.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerAdvice {
    @ExceptionHandler(LoginException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleRegistrationException(LoginException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleGetUserException(UsernameNotFoundException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }
}
