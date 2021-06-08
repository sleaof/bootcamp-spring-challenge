package com.digitalhouse.demo.exceptions;

public class BadRequestException extends RuntimeException{

    private String message;

    public BadRequestException(String message) {
        this.message = message;
    }
}