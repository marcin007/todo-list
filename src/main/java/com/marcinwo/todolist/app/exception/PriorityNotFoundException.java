package com.marcinwo.todolist.app.exception;

public class PriorityNotFoundException extends RuntimeException{
    public PriorityNotFoundException(String message) {
        super(message);
    }
}
