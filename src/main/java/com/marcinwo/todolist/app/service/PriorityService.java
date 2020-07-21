package com.marcinwo.todolist.app.service;

import com.marcinwo.todolist.app.entity.Priority;
import com.marcinwo.todolist.app.exception.PriorityNotFoundException;
import com.marcinwo.todolist.app.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {


    private PriorityRepository priorityRepository;

    @Autowired
    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public Priority findById(Long id){
        return priorityRepository.findById(id).orElseThrow(() -> new PriorityNotFoundException("Priority not found."));
    }
}
