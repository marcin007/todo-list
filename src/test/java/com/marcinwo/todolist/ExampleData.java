package com.marcinwo.todolist;

import com.marcinwo.todolist.api.dto.LoggedInUserDTO;
import com.marcinwo.todolist.app.entity.User;

import java.util.HashSet;
import java.util.List;

public class ExampleData {

    public static User getUsers() {
        return new User("Jacek", "Jackowski", "jacex", "123", "www.asd.pl",
                "qwe@gmail.com", "111", false, true, false, false,
                new HashSet<>(), new HashSet<>(), new HashSet<>());
    }


    public static List<User> getUserList() {
        return List.of(new User("Jacek", "Jackowski", "jacex", "123", "www.asd.pl",
                        "asd@gmail.com", "111", false, true, false, false,
                        new HashSet<>(), new HashSet<>(), new HashSet<>()),
                new User("Magda", "Magdowska", "magdax", "321", "www.qwe.pl",
                        "qwe@gmail.com", "222", false, true, false, false,
                        new HashSet<>(), new HashSet<>(), new HashSet<>()));
    }

    public static List<LoggedInUserDTO> getLoggedInUserDtos(){
        return List.of(new LoggedInUserDTO("www.asd.com", "asd@gmail.com", false, true, false,false),
                new LoggedInUserDTO("www.qwe.com", "qwe@gmail.com", false, true, false,false));
    }
}
