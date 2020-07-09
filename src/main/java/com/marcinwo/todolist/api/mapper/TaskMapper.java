package com.marcinwo.todolist.api.mapper;

import com.marcinwo.todolist.api.dto.TaskDTO;
import com.marcinwo.todolist.app.entity.Task;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toTaskDto (Task task);
    List<TaskDTO> toTaskDto(Collection<Task> tasks);
}
