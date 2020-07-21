package com.marcinwo.todolist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long taskBoardId;
    private Long priorityId;
    private Long owner;
    private String name;
    private String description;
    private String status;
    private LocalDateTime deadline;
    private LocalDateTime reminder;
    private Boolean hasExpired = false;
}
