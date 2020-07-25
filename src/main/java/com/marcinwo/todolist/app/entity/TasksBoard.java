package com.marcinwo.todolist.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks_boards")
public class TasksBoard extends AbstractEntity {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "You must choose a colour")
    private String colour;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "board_users",
            joinColumns = @JoinColumn(name = "tasks_board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();


    @OneToMany(mappedBy = "tasksBoard")
    private Set<Task> tasks =  new HashSet<>();



}
