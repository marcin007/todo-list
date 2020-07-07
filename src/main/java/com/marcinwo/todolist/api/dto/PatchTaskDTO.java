package com.marcinwo.todolist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchTaskDTO {

    private String name;
    private String description;
    private String status;

    private LocalDateTime deadline;
    private LocalDateTime reminder;
}
