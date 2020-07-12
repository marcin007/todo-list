package com.marcinwo.todolist.app;

import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.repository.UserRepository;
import com.marcinwo.todolist.app.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    private UserRepository userRepository;

    @Autowired
    public CurrentUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get() {
        String username = SecurityUtils.getUsername();
        User user = userRepository.findByUserNameAndHasExpiredFalse(username).orElseThrow();// TODO: 12.07.2020
        return user;
    }

}
