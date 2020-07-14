package com.marcinwo.todolist.app.security;

import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameAndIsEnabledTrueAndHasExpiredFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));

        return new UserDetailsImpl(user);

    }
}
