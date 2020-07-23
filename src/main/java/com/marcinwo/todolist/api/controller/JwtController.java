package com.marcinwo.todolist.api.controller;

import com.marcinwo.todolist.AppConstants;
import com.marcinwo.todolist.app.security.jwt.JwtAuthenticationRequest;
import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.security.jwt.JwtAuthenticationResponse;
import com.marcinwo.todolist.app.security.jwt.JwtTokenProvider;
import com.marcinwo.todolist.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(AppConstants.API_PREFIX + "/authorize")
@RestController
public class JwtController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtController(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) {
        String username = jwtAuthenticationRequest.getUsername();
        String password = jwtAuthenticationRequest.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = userService.findByUsername(username);
        String token = jwtTokenProvider.createToken(user);

        return ResponseEntity.ok(new JwtAuthenticationResponse(username, token));
    }

}
