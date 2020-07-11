package com.marcinwo.todolist.app.exception;

import com.marcinwo.todolist.api.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handlerUserNotFoundException(WebRequest request, UserNotFoundException e){
        return new ResponseEntity<>(new ApiError("User not found exception", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handlerTaskNotFoundException(WebRequest request, TaskNotFoundException e){
        return new ResponseEntity<>(new ApiError("Task not found exception", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskBoardNotFoundException.class)
    public ResponseEntity<ApiError> handlerTaskBoardNotFoundException(WebRequest request, TaskBoardNotFoundException e){
        return new ResponseEntity<>(new ApiError("Task board not found exception", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<ApiError> handlerUserUnauthorizedException(WebRequest w, UserUnauthorizedException e){
        return new ResponseEntity<>(new ApiError("User unauthorized.", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }
}
