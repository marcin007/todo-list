package com.marcinwo.todolist.controller;

import com.marcinwo.todolist.repository.TaskBoardRepository;
import com.marcinwo.todolist.service.TaskBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskBoardController {

    private TaskBoardService taskBoardService;
    private TaskBoardRepository taskBoardRepository;

    @Autowired
    public TaskBoardController(TaskBoardService taskBoardService, TaskBoardRepository taskBoardRepository) {
        this.taskBoardService = taskBoardService;
        this.taskBoardRepository = taskBoardRepository;
    }
}
