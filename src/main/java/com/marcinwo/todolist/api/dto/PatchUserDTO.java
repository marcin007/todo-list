package com.marcinwo.todolist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String avatarUrl;
    private String password;
}
