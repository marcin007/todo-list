package com.marcinwo.todolist.app.service;

import com.marcinwo.todolist.api.dto.PatchTaskBoardDTO;
import com.marcinwo.todolist.app.entity.TasksBoard;
import com.marcinwo.todolist.app.exception.TasksBoardNotFoundException;
import com.marcinwo.todolist.app.exception.UserUnauthorizedException;
import com.marcinwo.todolist.app.repository.TaskBoardRepository;
import com.marcinwo.todolist.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class TaskBoardService {

    private TaskBoardRepository taskBoardRepository;
    private TaskRepository taskRepository;

    @Autowired
    public TaskBoardService(TaskBoardRepository taskBoardRepository, TaskRepository taskRepository) {
        this.taskBoardRepository = taskBoardRepository;
        this.taskRepository = taskRepository;
    }

    public Set<TasksBoard> findAllByUsername(String username) {
        return taskBoardRepository.findAllByUsersUserName(username);
    }

    public TasksBoard findById(Long id) {
        return taskBoardRepository.findAllByIdAndHasExpiredFalse(id)
                .orElseThrow(() -> new TasksBoardNotFoundException("Tasks Board not found."));
    }

    public TasksBoard updateTasksBoard(Long id, PatchTaskBoardDTO patchTaskBoardDTO) {
        TasksBoard tasksBoard = findById(id);
        return updateTaskBoard(tasksBoard, patchTaskBoardDTO);
    }

    public TasksBoard updateTaskBoard(TasksBoard tasksBoard, PatchTaskBoardDTO patchTaskBoardDTO) {
        if (patchTaskBoardDTO.getColour() != null) {
            tasksBoard.setColour(patchTaskBoardDTO.getColour());
        }
        if (patchTaskBoardDTO.getName() != null) {
            tasksBoard.setName(patchTaskBoardDTO.getName());
        }
        return taskBoardRepository.save(tasksBoard);
    }

    public TasksBoard updateUserTasksBoard(Long id, Long currentUserId, PatchTaskBoardDTO patchTaskBoardDTO) {
        TasksBoard tasksBoard = findById(id);
        if (!tasksBoard.getOwner().getId().equals(currentUserId)) {
            throw new UserUnauthorizedException("User unauthorized.");
        }

        return updateTaskBoard(tasksBoard, patchTaskBoardDTO);
    }

    public void deleteById(Long id) {
        TasksBoard tasksBoard = findById(id);
        tasksBoard.setHasExpired(true);
        taskBoardRepository.save(tasksBoard);
    }

    public void deleteUserTasksBoardById(Long id, Long userId) {
        TasksBoard tasksBoard = taskBoardRepository.findByIdAndOwnerIdAndHasExpiredFalse(id, userId)
                .orElseThrow(() -> new TasksBoardNotFoundException("Tasks Board not found."));
        tasksBoard.setHasExpired(true);
        taskBoardRepository.save(tasksBoard);
    }

    public TasksBoard save(TasksBoard tasksBoard) {
        return taskBoardRepository.save(tasksBoard);
    }
}

