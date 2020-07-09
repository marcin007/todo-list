package com.marcinwo.todolist.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    @NonNull
    @Column(unique = true)
    private String userName;

    @NonNull
    private String password;

    private String avatarUrl;
    private String email;

    @Column(unique = true)
    private String activationCode;

    private boolean isExpired = false;
    private boolean isEnabled = false;
    private boolean isLocked = false;
    private boolean isCredentialsExpired = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<TasksBoard> tasksBoards = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Task> tasks =  new HashSet<>();

    public boolean hasRole(String role){
        return roles.stream().anyMatch(role1 -> role1.getName().equals(role));
    }

}
