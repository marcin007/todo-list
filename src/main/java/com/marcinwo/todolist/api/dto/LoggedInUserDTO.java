package com.marcinwo.todolist.api.dto;

public class LoggedInUserDTO extends UserDTO{

    private Long id;
    private String name;
    private String lastName;
    private String userName;
    private String avatarUrl;
    private String email;

    private boolean isExpired ;
    private boolean isEnabled;
    private boolean isLocked;
    private boolean isCredentialsExpired;
}
