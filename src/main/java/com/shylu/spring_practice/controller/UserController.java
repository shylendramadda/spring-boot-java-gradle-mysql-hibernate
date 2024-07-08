package com.shylu.spring_practice.controller;

import com.shylu.spring_practice.dto.UserDTO;
import com.shylu.spring_practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @GetMapping
    public UserDTO getUser(@RequestBody UserDTO userDTO) {
        return  userService.getUser(userDTO);
    }
}
