package com.marcinwo.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {

    private PriorityService priorityService;

    @Autowired
    public PriorityService(PriorityService priorityService) {
        this.priorityService = priorityService;
    }
}
