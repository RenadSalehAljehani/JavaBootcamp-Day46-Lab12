package com.example.blogsystem_lab12.Api;

public class ApiException extends RuntimeException {
    public ApiException(String message){
        super(message);
    }
}