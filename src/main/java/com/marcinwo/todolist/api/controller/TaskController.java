package com.marcinwo.todolist.api.controller;

import com.marcinwo.todolist.AppConstants;
import com.marcinwo.todolist.api.ApiInfo;
import com.marcinwo.todolist.api.dto.PatchTaskDTO;
import com.marcinwo.todolist.api.dto.TaskDTO;
import com.marcinwo.todolist.api.mapper.TaskMapper;
import com.marcinwo.todolist.app.CurrentUser;
import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.exception.UserUnauthorizedException;
import com.marcinwo.todolist.app.security.annotation.IsAuthenticated;
import com.marcinwo.todolist.app.service.TaskBoardService;
import com.marcinwo.todolist.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(AppConstants.API_PREFIX)
public class TaskController {

    private TaskService taskService;
    private TaskBoardService taskBoardService;
    private CurrentUser currentUser;
    private TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskBoardService taskBoardService, CurrentUser currentUser, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskBoardService = taskBoardService;
        this.currentUser = currentUser;
        this.taskMapper = taskMapper;
    }

    @IsAuthenticated
    @GetMapping("/tasks/{id}")
    public TaskDTO getTask(@PathVariable Long id) {
        Task task = taskService.findById(id);
        User user = currentUser.get();
        if (task.getTasksBoard().getUsers().contains(user) || task.getOwner().equals(user)) {
            return taskMapper.toTaskDto(taskService.findById(id));
        } else {
            throw new UserUnauthorizedException("User unauthorized.");
        }
    }

    @IsAuthenticated
    @PostMapping("/tasks")
    public TaskDTO postTask(@RequestBody TaskDTO taskDTO) {
        return taskMapper.toTaskDto(taskService.save(taskMapper.toTaskEntity(taskDTO)));
    }

    @IsAuthenticated
    @PatchMapping("/tasks/{id}")
    public TaskDTO patchTask(@PathVariable Long id, @RequestBody PatchTaskDTO patchTaskDTO) {
        Task task = taskService.findById(id);
        User user = currentUser.get();
        if (task.getTasksBoard().getUsers().contains(user) || task.getOwner().equals(user)) {
            return taskMapper.toTaskDto(taskService.updateTask(id, patchTaskDTO));
        } else {
            throw new UserUnauthorizedException("User unauthorized.");
        }
    }

    @IsAuthenticated
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<ApiInfo> deleteTask(@PathVariable Long id) {
        Task task = taskService.findById(id);
        User user = currentUser.get();
        if (task.getTasksBoard().getUsers().contains(user) || task.getOwner().equals(user)) {
            taskService.deleteById(id);
            return new ResponseEntity<>(new ApiInfo("Task deleted.", HttpStatus.OK.value()), HttpStatus.OK);
        } else {
            throw new UserUnauthorizedException("User unauthorized.");
        }
    }

    @IsAuthenticated
    @GetMapping("/user/tasks")
    public List<TaskDTO> getTasks() {
        Long userId = currentUser.get().getId();
        return taskMapper.toTaskDto(taskService.findAllByUserId(userId));
    }


    @IsAuthenticated //todo nie zwraca wspólnych taskow, czemu?
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    @GetMapping("/users/{username}/tasks")
    public List<TaskDTO> getTasks(@PathVariable String username) {
        return taskMapper.toTaskDto(taskService.findAllByUsername(username));
    }


    @IsAuthenticated
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    @PatchMapping("/users/{username}/tasks{id}")
    public TaskDTO updateTask(@PathVariable String username, @PathVariable Long id) {
        return taskMapper.toTaskDto(taskService.updateTask(username, id));
    }


    @IsAuthenticated
    @PreAuthorize("hasRole('ROLE_ADMIN') or taskBoardService.findById(#id).users.contains(currentUser.get())")
    @GetMapping("/boards/{id}/tasks")
    public List<TaskDTO> getTasksFromBoard(@PathVariable Long id) {
        return taskMapper.toTaskDto(taskService.findAllByTasksBoardId(id));
    }
}
