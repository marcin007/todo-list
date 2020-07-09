package com.marcinwo.todolist.app.service;

import com.marcinwo.todolist.api.dto.PatchTaskBoardDTO;
import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.entity.TasksBoard;
import com.marcinwo.todolist.app.exception.TaskBoardNotFoundException;
import com.marcinwo.todolist.app.exception.TaskNotFoundException;
import com.marcinwo.todolist.app.repository.TaskBoardRepository;
import com.marcinwo.todolist.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskBoardService {

    private TaskBoardRepository taskBoardRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TaskBoardService(TaskBoardRepository taskBoardRepository, TaskRepository taskRepository) {
        this.taskBoardRepository = taskBoardRepository;
        this.taskRepository = taskRepository;
    }

    public List<TasksBoard> findAll() {
        return taskBoardRepository.findAll().stream()
                .filter(tasksBoard -> !tasksBoard.isHasExpired())
                .collect(Collectors.toList());
    }

    public TasksBoard findById(Long id) {
        return taskBoardRepository.findById(id)
                .filter(t -> !t.isHasExpired())
                .orElseThrow(() -> new TaskBoardNotFoundException("Tasks Board not found."));
    }

    public TasksBoard updateTasksBoard(Long id, PatchTaskBoardDTO patchTaskBoardDTO) {
        TasksBoard tasksBoard = findById(id);
        if(patchTaskBoardDTO.getColour() != null){
            tasksBoard.setColour(patchTaskBoardDTO.getColour());
        }
        if(patchTaskBoardDTO.getName() != null){
            tasksBoard.setName(patchTaskBoardDTO.getName());
        }
        return taskBoardRepository.save(tasksBoard);
    }

    public void deleteById(Long id) {
    TasksBoard tasksBoard = findById(id);
    tasksBoard.setHasExpired(true);
    taskBoardRepository.save(tasksBoard);
    }
}

