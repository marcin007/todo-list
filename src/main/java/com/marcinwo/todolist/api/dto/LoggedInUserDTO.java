package com.marcinwo.todolist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoggedInUserDTO extends UserDTO {

    private String avatarUrl;
    private String email;

    private boolean isExpired;
    private boolean isEnabled;
    private boolean isLocked;
    private boolean isCredentialsExpired;
}
