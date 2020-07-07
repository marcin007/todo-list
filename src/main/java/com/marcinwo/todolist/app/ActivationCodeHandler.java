package com.marcinwo.todolist.app;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ActivationCodeHandler {

    public String generateActivationCode() {
        return UUID.randomUUID().toString();
    }

}
