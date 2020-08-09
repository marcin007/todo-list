package com.marcinwo.todolist.api.controller;

import com.marcinwo.todolist.AppConstants;
import com.marcinwo.todolist.api.ApiInfo;
import com.marcinwo.todolist.api.dto.LoggedInUserDTO;
import com.marcinwo.todolist.api.dto.PatchUserDTO;
import com.marcinwo.todolist.api.dto.UserDTO;
import com.marcinwo.todolist.api.mapper.UserMapper;
import com.marcinwo.todolist.app.security.SecurityUtils;
import com.marcinwo.todolist.app.security.annotation.IsAuthenticated;
import com.marcinwo.todolist.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.API_PREFIX)
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @IsAuthenticated
    @GetMapping("/user")
    public LoggedInUserDTO getLoggedInUser() {
        return userMapper.toLoggedInUserDTO(userService.findCurrentUser());
    }

    @GetMapping("/users")
    public ResponseEntity<List<? extends UserDTO>> getUsers() {
        if (SecurityUtils.isUserLoggedIn() && userService.findCurrentUser().hasRole("ROLE_ADMIN")) {
            return ResponseEntity.ok(userMapper.toLoggedInUserDTO(userService.findAll()));
        } else {
            return ResponseEntity.ok(userMapper.toUserDto(userService.findAll()));
        }
    }

    @IsAuthenticated
    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        if (userService.findCurrentUser().hasRole("ROLE_ADMIN")) {
            return userMapper.toLoggedInUserDTO(userService.findById(id));
        } else {
            return userMapper.toUserDto(userService.findById(id));
        }
    }

    @PostMapping("/users")
    public UserDTO postUser(@RequestBody UserDTO userDTO) {
        //email, sms service
        return userMapper.toUserDto(userService.save(userMapper.toUserEntity(userDTO)));
    }


    @IsAuthenticated
    @PreAuthorize("hasRole('ROLE_ADMIN') or userService.findCurrentUser().getId().equals(#id)")
    @PatchMapping("/users/{id}")
    public LoggedInUserDTO updateUser(@PathVariable Long id, @RequestBody PatchUserDTO patchUserDTO) {
        return userMapper.toLoggedInUserDTO(userService.updateUser(id, patchUserDTO));

    }

    @IsAuthenticated
    @PreAuthorize("hasRole('ROLE_ADMIN') or userService.findCurrentUser().getId().equals(#id)")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiInfo> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiInfo("User deleted.", HttpStatus.OK.value()), HttpStatus.OK);
    }
}
