package com.marcinwo.todolist.controller;

import com.marcinwo.todolist.repository.TaskRepository;
import com.marcinwo.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private TaskService taskService;
    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }
}
