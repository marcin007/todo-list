package com.marcinwo.todolist.app.exception;

public class TasksBoardNotFoundException extends RuntimeException{

    public TasksBoardNotFoundException(String message) {
        super(message);
    }
}
