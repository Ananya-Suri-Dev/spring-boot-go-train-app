package com.rbc.springbootgotrainapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LineException {

    private final String message;
    private final HttpStatus httpStatus;

    public LineException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
