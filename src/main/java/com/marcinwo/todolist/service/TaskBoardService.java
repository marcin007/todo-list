package com.marcinwo.todolist.service;

import com.marcinwo.todolist.repository.TaskBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskBoardService {

    private TaskBoardRepository taskBoardRepository;

    @Autowired
    public TaskBoardService(TaskBoardRepository taskBoardRepository) {
        this.taskBoardRepository = taskBoardRepository;
    }
}
