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
@Table(name = "users")
public class User extends AbstractEntity {

    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String avatarUrl;
    private String activationCode;

    private boolean isExpired;
    private boolean isEnabled;
    private boolean isLocked;
    private boolean isCredentialsExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<TasksBoard> tasksBoards = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Task> tasks = new ArrayList<>();


}
