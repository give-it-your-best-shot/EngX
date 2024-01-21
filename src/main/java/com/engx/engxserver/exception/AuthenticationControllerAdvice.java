package com.engx.engxserver.exception;

import com.engx.engxserver.controller.AuthenticationController;
import com.engx.engxserver.dto.ResponseFailure;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
public class AuthenticationControllerAdvice {
    @ExceptionHandler(InsertFailException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleInsertFailException(InsertFailException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }

    @ExceptionHandler(MissingArgumentException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleInsertFailException(
            MissingArgumentException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }

    @ExceptionHandler(UnAuthenticationException.class)
    ResponseEntity<ResponseFailure<ApiExceptionResponse>> handleInsertFailException(
            UnAuthenticationException exception) {

        final ApiExceptionResponse response =
                new ApiExceptionResponse(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        ResponseFailure<ApiExceptionResponse> responseFailure = new ResponseFailure<>(response);
        return ResponseEntity.ok(responseFailure);
    }
}
