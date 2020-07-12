package com.marcinwo.todolist.api.mapper;

import com.marcinwo.todolist.api.dto.LoggedInUserDTO;
import com.marcinwo.todolist.api.dto.UserDTO;
import com.marcinwo.todolist.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDto(User user);
    List<UserDTO> toUserDto(Collection<User> userCollection);


    LoggedInUserDTO toLoggedInUserDTO(User user);

    User toUserEntity(UserDTO userDTO);
}
