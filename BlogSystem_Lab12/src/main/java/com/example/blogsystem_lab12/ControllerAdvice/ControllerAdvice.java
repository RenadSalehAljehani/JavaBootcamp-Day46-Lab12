package com.example.blogsystem_lab12.ControllerAdvice;

import com.example.blogsystem_lab12.Api.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity apiException(ApiException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }
}