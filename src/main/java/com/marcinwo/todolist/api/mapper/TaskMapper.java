package com.marcinwo.todolist.api.mapper;

import com.marcinwo.todolist.api.dto.TaskDTO;
import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.repository.PriorityRepository;
import com.marcinwo.todolist.app.service.PriorityService;
import com.marcinwo.todolist.app.service.TaskBoardService;
import com.marcinwo.todolist.app.service.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private PriorityService priorityService;

    @Autowired
    private TaskBoardService taskBoardService;

    public TaskDTO toTaskDto (Task task){
        if(task == null){
            return null;
        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setReminder(task.getReminder());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setName(task.getName());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setOwner(task.getOwner().getId());
        taskDTO.setTaskBoardId(task.getTasksBoard().getId());
        taskDTO.setPriorityId(task.getPriority().getId());
        return taskDTO;
    }

    public abstract List<TaskDTO> toTaskDto(Collection<Task> tasks);


    public Task toTaskEntity(TaskDTO taskDTO){
        Task task = new Task();
        if (taskDTO.getName() != null) {
            task.setName(taskDTO.getName());
        }
        if (taskDTO.getOwner() != null) {
            task.setOwner(userService.findById(taskDTO.getOwner()));
        }
        if (taskDTO.getPriorityId() != null) {
            task.setPriority(priorityService.findById(taskDTO.getPriorityId()));
        }
        if(taskDTO.getTaskBoardId() != null){
            task.setTasksBoard(taskBoardService.findById(taskDTO.getTaskBoardId()));
        }
        if(taskDTO.getHasExpired() != null){
            task.setHasExpired(taskDTO.getHasExpired());
        }
        if(taskDTO.getDescription() != null){
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getDeadline() != null) {
            task.setDeadline(taskDTO.getDeadline());
        }
        if (taskDTO.getReminder() != null) {
            task.setReminder(taskDTO.getReminder());
        }
        if(taskDTO.getStatus() != null){
            task.setStatus(taskDTO.getStatus());
        }
        return task;
    }
}
