package com.digitalhouse.demo.exceptions.user;

public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(String message) {
        this.message = message;
    }
}
