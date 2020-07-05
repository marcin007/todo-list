package com.marcinwo.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atmosphere.config.service.Get;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "tasks_board_id", nullable = false)
    private TasksBoard tasksBoards;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_users",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    private String name;
    private String description;
    private String status;

    private LocalDateTime deadline;
    private LocalDateTime reminder;
}
