package com.engx.engxserver.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MissingArgumentException extends Exception {
    private final String message;
}
