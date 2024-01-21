package com.engx.engxserver.exception;

import com.engx.engxserver.controller.MaterialController;
import com.engx.engxserver.dto.ResponseFailure;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = MaterialController.class)
public class ChapterControllerAdvice {
    @ExceptionHandler(InsertFailException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleInsertFailException(InsertFailException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleResourceNotFoundException(
            ResourceNotFoundException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }
}
