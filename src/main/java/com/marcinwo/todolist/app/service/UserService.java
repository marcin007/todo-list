package com.marcinwo.todolist.app.service;

import com.marcinwo.todolist.api.dto.PatchUserDTO;
import com.marcinwo.todolist.app.ActivationCodeHandler;
import com.marcinwo.todolist.app.exception.UserNotFoundException;
import com.marcinwo.todolist.app.repository.*;
import com.marcinwo.todolist.app.entity.*;
import com.marcinwo.todolist.app.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private ActivationCodeHandler activationCodeHandler;

    @Autowired
    public UserService(UserRepository userRepository, ActivationCodeHandler activationCodeHandler) {
        this.userRepository = userRepository;
        this.activationCodeHandler = activationCodeHandler;
    }
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findCurrentUser(){
        return userRepository.findByUserName(SecurityUtils.getUsername())
                .orElseThrow(()->new UserNotFoundException("Username not found."));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public User save(User user) {
        user.setActivationCode(activationCodeHandler.generateActivationCode());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



    public User updateUser(Long id, PatchUserDTO patchUserDTO) {
        User user = findById(id);
        if (patchUserDTO.getName() != null) {
            user.setName(patchUserDTO.getName());
        }
        if (patchUserDTO.getName() != null) {
            user.setLastName(patchUserDTO.getLastName());
        }
        if (patchUserDTO.getUserName() != null) {
            user.setUserName(patchUserDTO.getUserName());
        }
        if (patchUserDTO.getAvatarUrl() != null) {
            user.setAvatarUrl(patchUserDTO.getAvatarUrl());
        }
        if(patchUserDTO.getPassword() != null){
            user.setPassword(passwordEncoder.encode(patchUserDTO.getPassword()));
        }
        return userRepository.save(user);
    }
}
