package com.marcinwo.todolist.api.dto;

import java.time.LocalDateTime;

public class TaskDTO {

    private Long taskBoardId;
    private Long priorityId;
    private Long userId;
    private String name;
    private String description;
    private String status;
    private LocalDateTime deadline;
    private LocalDateTime reminder;
}
