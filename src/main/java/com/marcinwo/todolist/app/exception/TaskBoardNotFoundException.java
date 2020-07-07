package com.marcinwo.todolist.app.exception;

public class TaskBoardNotFoundException extends RuntimeException{

    public TaskBoardNotFoundException(String message) {
        super(message);
    }
}
