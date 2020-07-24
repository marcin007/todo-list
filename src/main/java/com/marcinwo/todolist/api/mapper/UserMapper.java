package com.marcinwo.todolist.api.mapper;

import com.marcinwo.todolist.api.dto.LoggedInUserDTO;
import com.marcinwo.todolist.api.dto.UserDTO;
import com.marcinwo.todolist.app.entity.User;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public UserDTO toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserName(user.getUserName());
        userDTO.setHasExpired(user.getHasExpired());

        return userDTO;
    }

    public List<UserDTO> toUserDto(Collection<User> users) {
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    public LoggedInUserDTO toLoggedInUserDTO(User user) {
        if (user == null) {
            return null;
        }

        LoggedInUserDTO loggedInUserDTO = new LoggedInUserDTO();

        loggedInUserDTO.setId(user.getId());
        loggedInUserDTO.setFirstName(user.getFirstName());
        loggedInUserDTO.setLastName(user.getLastName());
        loggedInUserDTO.setUserName(user.getUserName());
        loggedInUserDTO.setHasExpired(user.getHasExpired());
        loggedInUserDTO.setAvatarUrl(user.getAvatarUrl());
        loggedInUserDTO.setEmail(user.getEmail());

        return loggedInUserDTO;
    }

    public List<LoggedInUserDTO> toLoggedInUserDTO(Collection<User> users) {
        return users.stream().map(this::toLoggedInUserDTO).collect(Collectors.toList());
    }

    public User toUserEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();

        user.setId(userDTO.getId());
        user.setHasExpired(userDTO.getHasExpired());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());

        return user;
    }
}
