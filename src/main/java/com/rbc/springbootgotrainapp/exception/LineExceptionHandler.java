package com.rbc.springbootgotrainapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LineExceptionHandler {


    //1. If Line does not exist return HTTP 404 (Not Found)
    @ExceptionHandler(value = {LineNotFoundException.class})
    public ResponseEntity<Object> handleLineNotFoundException(LineNotFoundException lineNotFoundException){
        //Creating payload which will be returned back in terms of response entity to the client
        LineException lineException = new LineException(
                lineNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(lineException, HttpStatus.NOT_FOUND);
    }

    //2. Return 400 for bad Date format request
    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<String> handleAppBadRequestException(AppBadRequestException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
