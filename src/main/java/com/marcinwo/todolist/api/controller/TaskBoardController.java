package com.marcinwo.todolist.api.controller;

import com.marcinwo.todolist.AppConstants;
import com.marcinwo.todolist.api.ApiInfo;
import com.marcinwo.todolist.api.dto.PatchTaskBoardDTO;
import com.marcinwo.todolist.api.dto.TasksBoardDTO;
import com.marcinwo.todolist.api.mapper.TasksBoardMapper;
import com.marcinwo.todolist.app.exception.UserUnauthorizedException;
import com.marcinwo.todolist.app.security.annotation.IsAuthenticated;
import com.marcinwo.todolist.app.service.TaskBoardService;
import com.marcinwo.todolist.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(AppConstants.API_PREFIX)
public class TaskBoardController {

    private TaskBoardService taskBoardService;
    private UserService userService;
    private TasksBoardMapper tasksBoardMapper;

    @Autowired
    public TaskBoardController(TaskBoardService taskBoardService, UserService userService, TasksBoardMapper tasksBoardMapper) {
        this.taskBoardService = taskBoardService;
        this.userService = userService;
        this.tasksBoardMapper = tasksBoardMapper;
    }

    @IsAuthenticated
    @GetMapping("/users/{username}/boards")
    public Set<TasksBoardDTO> getAdminOrCurrentUserTasksBoardsById(@PathVariable String username) {
        if ((userService.findCurrentUser().hasRole("ROLE_ADMIN")) || userService.findCurrentUser().getUserName().equals(username)) {
            return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findAllByUsername(username));
        } else {
            throw new UserUnauthorizedException("User unauthorized");
        }
    }

    @IsAuthenticated
    @GetMapping("/user/boards")
    public Set<TasksBoardDTO> getTasksBoards() {
        String user = userService.findCurrentUser().getUserName();
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findAllByUsername(user));
    }


    @IsAuthenticated
    @PostAuthorize("returnObject.collaborators.contains(authentication.principal.username)")
    @GetMapping("/user/boards/{id}")
    public TasksBoardDTO getTasksBoardsById(@PathVariable Long id) {
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findById(id));
    }

    @IsAuthenticated
    @PostMapping("/user/boards")
    public TasksBoardDTO getTasksBoardsById(@RequestBody TasksBoardDTO tasksBoardDTO) {
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.save(tasksBoardMapper.toTasksBoardEntity(tasksBoardDTO)));
    }

    @IsAuthenticated
    @PatchMapping("/user/boards/{id}")
    public TasksBoardDTO patchTasksBoard(@PathVariable Long id, @RequestBody PatchTaskBoardDTO patchTaskBoardDTO) {
        Long currentUserId = userService.findCurrentUser().getId();
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.updateUserTasksBoard(id, currentUserId, patchTaskBoardDTO));
    }

    @IsAuthenticated
    @DeleteMapping("/user/boards/{id}")
    public ResponseEntity<ApiInfo> deleteById(@PathVariable Long id) {
        Long currentUserId = userService.findCurrentUser().getId();
        taskBoardService.deleteUserTasksBoardById(id, currentUserId);
        return new ResponseEntity<>(new ApiInfo("Tasks Board deleted.", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @IsAuthenticated
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/boards/{id}")
    public TasksBoardDTO adminGetTaskBoard(@PathVariable Long id) {
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findById(id));
    }

    @IsAuthenticated
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/boards/{id}")
    public TasksBoardDTO adminPatchTasksBoard(@PathVariable Long id, @RequestBody PatchTaskBoardDTO patchTaskBoardDTO) {
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.updateTasksBoard(id, patchTaskBoardDTO));
    }

    @IsAuthenticated
    @DeleteMapping("/boards/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiInfo> adminDeleteById(@PathVariable Long id) {
        taskBoardService.deleteById(id);
        return new ResponseEntity<>(new ApiInfo("Tasks Board deleted.", HttpStatus.OK.value()), HttpStatus.OK);
    }
}
