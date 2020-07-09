package com.marcinwo.todolist.api.controller;

import com.marcinwo.todolist.api.ApiInfo;
import com.marcinwo.todolist.api.dto.PatchTaskBoardDTO;
import com.marcinwo.todolist.api.dto.TasksBoardDTO;
import com.marcinwo.todolist.api.mapper.TasksBoardMapper;
import com.marcinwo.todolist.app.exception.UserUnauthorizedException;
import com.marcinwo.todolist.app.security.annotation.IsAdminOrCurrentUser;
import com.marcinwo.todolist.app.security.annotation.IsAuthenticated;
import com.marcinwo.todolist.app.service.TaskBoardService;
import com.marcinwo.todolist.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
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

    //    GET /users/{id}/boards - admin albo jesli zalogowany ma takie id  //todo ok?
    @IsAdminOrCurrentUser
    @IsAuthenticated
    @GetMapping("/users/{id}/boards")
    public Set<TasksBoardDTO> getAdminOrCurrentUserTasksBoardsById(@PathVariable Long id){
        if((userService.findCurrentUser().hasRole("ROLE_ADMIN")) || userService.findCurrentUser().getId().equals(id)){
            return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findAll());
        }
        else {
        throw new UserUnauthorizedException("User unauthorized");
        }
    }

    //    GET /user/boards (może wejść tylko osoba zautoryzowana)//todo ok?
    @GetMapping("/user/boards")
    public Set<TasksBoardDTO> getTasksBoards(){
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findAll());
    }

//    GET /user/boards/{id} //todo ok?
    @GetMapping("/user/boards/{id}")
    public TasksBoardDTO getTasksBoardsById(@PathVariable Long id){
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.findById(id));
    }

//    PATCH /user/boards/{id} //todo ok?
    @PatchMapping("/user/boards/{id}")
    public TasksBoardDTO patchTasksBoard(@PathVariable Long id, @RequestBody PatchTaskBoardDTO patchTaskBoardDTO){
        return tasksBoardMapper.toTasksBoardDTO(taskBoardService.updateTasksBoard(id, patchTaskBoardDTO));
    }

//    DELETE /user/boards/{id} //todo ok?
    @DeleteMapping("/user/boards/{id}")
    public ResponseEntity<ApiInfo> deleteById(@PathVariable Long id){
        taskBoardService.deleteById(id);
        return new ResponseEntity<>(new ApiInfo("Tasks Board deleted.", HttpStatus.OK.value()), HttpStatus.OK);
    }

//    GET /boards/{id}
//    PATCH /boards/{id}
//    DELETE /boards/{id}

}
