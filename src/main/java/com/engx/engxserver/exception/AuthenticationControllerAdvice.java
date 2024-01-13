package com.engx.engxserver.exception;

import com.engx.engxserver.controller.AuthenticationController;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
public class AuthenticationControllerAdvice {
    @ExceptionHandler(InsertFailException.class)
    ResponseEntity<ApiExceptionResponse> handleInsertFailException(InsertFailException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
