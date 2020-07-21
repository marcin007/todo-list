package com.marcinwo.todolist.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "tasks_board_id", nullable = false)
    private TasksBoard tasksBoard;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "task_users",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "priority_id", nullable = false)
    private Priority priority;

    private String name;
    private String description;
    private String status;

    private LocalDateTime deadline;
    private LocalDateTime reminder;
}
