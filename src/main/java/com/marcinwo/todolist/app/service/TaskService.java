package com.marcinwo.todolist.app.service;

import com.marcinwo.todolist.api.dto.PatchTaskDTO;
import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.exception.TaskNotFoundException;
import com.marcinwo.todolist.app.exception.UserNotFoundException;
import com.marcinwo.todolist.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id) {
        return taskRepository.findByIdAndHasExpiredFalse(id).orElseThrow(() -> new TaskNotFoundException("Task not found."));
    }

    public void deleteById(Long id) {
        Task task = findById(id);
        task.setHasExpired(true);
        taskRepository.save(task);
    }

    public Set<Task> findAllByUserId(Long id) {
        return taskRepository.findAllByUsersIdAndHasExpiredFalse(id);
    }


    public List<Task> findAllByUsername(String username) {
        return taskRepository.findAllByUsersUserNameAndHasExpiredFalse(username);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, PatchTaskDTO patchTaskDTO) {
        Task task = findById(id);
        if (patchTaskDTO.getName() != null) {
            task.setName(patchTaskDTO.getName());
        }
        if (patchTaskDTO.getDescription() != null) {
            task.setDescription(patchTaskDTO.getDescription());
        }
        if (patchTaskDTO.getStatus() != null) {
            task.setStatus(patchTaskDTO.getStatus());
        }
        if (patchTaskDTO.getDeadline() != null) {
            task.setDeadline(patchTaskDTO.getDeadline());
        }
        if(patchTaskDTO.getReminder() != null){
            task.setReminder(patchTaskDTO.getReminder());
        }
      return taskRepository.save(task);
    }

    public Task updateTask(String username, Long id) {
    return null;
    }
}
