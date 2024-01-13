package com.engx.engxserver.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InsertFailException extends Exception {
    private final String message;
}
