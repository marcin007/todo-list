package com.marcinwo.todolist.controller;

import com.marcinwo.todolist.ExampleData;
import com.marcinwo.todolist.api.JsonUtils;
import com.marcinwo.todolist.api.controller.UserController;
import com.marcinwo.todolist.api.dto.LoggedInUserDTO;
import com.marcinwo.todolist.api.mapper.UserMapper;
import com.marcinwo.todolist.app.entity.Role;
import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.entity.TasksBoard;
import com.marcinwo.todolist.app.entity.User;
import com.marcinwo.todolist.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;


import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;


    @Test
    public void given_LoggedInUserIsAdmin_when_getUser_then_returnUsersList() throws Exception{

    }

}
