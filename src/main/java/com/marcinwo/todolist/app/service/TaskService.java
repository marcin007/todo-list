package com.marcinwo.todolist.app.service;

import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.exception.UserNotFoundException;
import com.marcinwo.todolist.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id) {
        return taskRepository.findByIdAndHasExpiredFalse(id).orElseThrow(()-> new UserNotFoundException("user not found."));
    }

    public void deleteById(Long id) {
    Task task = findById(id);
    task.setHasExpired(true);
    taskRepository.save(task);
    }


    public Set<Task> findAllByUserId(Long id) {
        return taskRepository.findAllByUsersId(id);
    }
}
