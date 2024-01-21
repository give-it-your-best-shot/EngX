package com.engx.engxserver.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnAuthenticationException extends Exception {
    private final String message;
}
