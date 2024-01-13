package com.engx.engxserver.exception;

import com.engx.engxserver.controller.ChapterController;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ChapterController.class)
public class ChapterControllerAdvice {
    @ExceptionHandler(InsertFailException.class)
    ResponseEntity<ApiExceptionResponse> handleInsertFailException(InsertFailException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
