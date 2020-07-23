package com.marcinwo.todolist.app.security.jwt;

public class InvalidJwtTokenException extends RuntimeException {

    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
