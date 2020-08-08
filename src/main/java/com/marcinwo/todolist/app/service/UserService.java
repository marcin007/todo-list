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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ActivationCodeHandler activationCodeHandler, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.activationCodeHandler = activationCodeHandler;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAllByHasExpiredFalse();
    }

    public List<User> findAllWithoutCurrentUser() {
        List<User> users = userRepository.findAllByHasExpiredFalse();
        users.remove(findCurrentUser());
        return users;
    }

    public User findCurrentUser() {
        return userRepository.findByUserNameAndHasExpiredFalse(SecurityUtils.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Username not found."));
    }

    public User findById(Long id) {
        return userRepository.findByIdAndHasExpiredFalse(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUserNameAndHasExpiredFalse(username);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(activationCodeHandler.generateActivationCode());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findByIdAndHasExpiredFalse(id).orElseThrow(() -> new UserNotFoundException("User not found."));
        user.setIsExpired(true);
        userRepository.save(user);
    }


    public User updateUser(Long id, PatchUserDTO patchUserDTO) {
        User user = findById(id);
        if (patchUserDTO.getFirstName() != null) {
            user.setFirstName(patchUserDTO.getFirstName());
        }
        if (patchUserDTO.getLastName() != null) {
            user.setLastName(patchUserDTO.getLastName());
        }
        if (patchUserDTO.getUserName() != null) {
            user.setUserName(patchUserDTO.getUserName());
        }
        if (patchUserDTO.getAvatarUrl() != null) {
            user.setAvatarUrl(patchUserDTO.getAvatarUrl());
        }
        return userRepository.save(user);
    }


    public User findByUsername(String username) {
        return userRepository.findByUserNameAndHasExpiredFalse(username).orElseThrow(() -> new RuntimeException("sd"));
    }
}
