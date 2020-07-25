package com.marcinwo.todolist.api.mapper;


import com.marcinwo.todolist.api.dto.TasksBoardDTO;
import com.marcinwo.todolist.app.CurrentUser;
import com.marcinwo.todolist.app.entity.TasksBoard;
import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.service.TaskBoardService;
import com.marcinwo.todolist.app.service.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TasksBoardMapper {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskBoardService taskBoardService;

    public TasksBoardDTO toTasksBoardDTO(TasksBoard tasksBoard) {
        if (tasksBoard == null) {
            return null;
        }

        TasksBoardDTO tasksBoardDTO = new TasksBoardDTO();
        tasksBoardDTO.setId(tasksBoard.getId());
        tasksBoardDTO.setColour(tasksBoard.getColour());
        tasksBoardDTO.setOwner(tasksBoard.getOwner().getUserName());
        tasksBoardDTO.setName(tasksBoard.getName());
        tasksBoardDTO.setCollaborators(tasksBoard.getUsers().stream().map(user -> user.getUserName()).collect(Collectors.toSet()));

        return tasksBoardDTO;
    }

    public Set<TasksBoardDTO> toTasksBoardDTO(Collection<TasksBoard> tasksBoards) {
        if (tasksBoards == null) {
            return null;
        }

        Set<TasksBoardDTO> set = new HashSet<TasksBoardDTO>(Math.max((int) (tasksBoards.size() / .75f) + 1, 16));
        for (TasksBoard tasksBoard : tasksBoards) {
            set.add(toTasksBoardDTO(tasksBoard));
        }

        return set;
    }

    public TasksBoard toTasksBoardEntity(TasksBoardDTO tasksBoardDTO) {
        TasksBoard tasksBoard = new TasksBoard();
        tasksBoard.setOwner(currentUser.get());
        if (tasksBoardDTO.getName() != null) {
            tasksBoard.setName(tasksBoardDTO.getName());
        }
        if (tasksBoardDTO.getColour() != null) {
            tasksBoard.setColour(tasksBoardDTO.getColour());
        }

        if (tasksBoardDTO.getCollaborators() != null) {
            Set<User> collaborators = tasksBoardDTO.getCollaborators().stream().map(username -> userService.findByUsername(username)).collect(Collectors.toSet());
            tasksBoard.setUsers(collaborators);
        }
    return taskBoardService.save(tasksBoard);
    }
}
