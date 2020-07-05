package com.marcinwo.todolist.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks_boards")
public class TasksBoard extends AbstractEntity {


    private String name;
    private String colour;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "board_users",
            joinColumns = @JoinColumn(name = "tasks_board_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();


    @OneToMany(mappedBy = "tasksBoards") // na podstawie tasks_boards zmapuj wszystkie tasks ( w task bd szukać zmiennej o nazwie tasks_boards;
    private List<Task> tasks = new ArrayList<>();



}
