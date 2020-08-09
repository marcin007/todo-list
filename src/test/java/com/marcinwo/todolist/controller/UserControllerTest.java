package com.marcinwo.todolist.controller;

import com.marcinwo.todolist.ExampleData;
import com.marcinwo.todolist.api.JsonUtils;
import com.marcinwo.todolist.api.controller.UserController;
import com.marcinwo.todolist.api.dto.LoggedInUserDTO;
import com.marcinwo.todolist.api.dto.UserDTO;
import com.marcinwo.todolist.api.mapper.UserMapper;
import com.marcinwo.todolist.app.entity.Role;
import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.entity.TasksBoard;
import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.repository.UserRepository;
import com.marcinwo.todolist.app.security.jwt.JwtTokenProvider;
import com.marcinwo.todolist.app.service.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;


import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static String token;

    @Before
    public void before() {

    }

    @Test
    public void given_UserDtoNotLogged_when_getUser_then_returnUsersList() throws Exception{

        //given
        List<User> users = ExampleData.getUserList();
        List<UserDTO> dtos = ExampleData.getUserDtoList();

        //when
        when(userService.findAll()).thenReturn(users);
        when(userMapper.toUserDto(anyCollection())).thenReturn(dtos);

        //then
        mockMvc.perform(get("/api/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtils.toJsonString(dtos)));
    }

    @Test
    public void asd() throws Exception{
        User admin = userRepository.findByUserNameAndHasExpiredFalse("m").orElseThrow();
        String token = jwtTokenProvider.createToken(admin);

        when(userService.findCurrentUser()).thenReturn(admin);

        //given
        List<User> users = ExampleData.getUserList();
        List<LoggedInUserDTO> dtos = ExampleData.getLoggedInUserDtos();

        //when
        when(userService.findAll()).thenReturn(users);
        when(userMapper.toLoggedInUserDTO(anyCollection())).thenReturn(dtos);

        mockMvc.perform(get("/api/users").header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }




}
