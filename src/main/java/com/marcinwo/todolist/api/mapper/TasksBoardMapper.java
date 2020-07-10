package com.marcinwo.todolist.api.mapper;


import com.marcinwo.todolist.api.dto.TasksBoardDTO;
import com.marcinwo.todolist.app.entity.TasksBoard;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TasksBoardMapper {

    TasksBoardDTO toTasksBoardDTO(TasksBoard tasksBoard);
    Set<TasksBoardDTO> toTasksBoardDTO(Collection<TasksBoard> tasksBoards);
}
