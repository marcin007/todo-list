package com.marcinwo.todolist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TasksBoardDTO {

    private Long id;
    private String owner;
    private String name;
    private String colour;
    private Set<String> collaborators;
    private Boolean hasExpired = false;

}
