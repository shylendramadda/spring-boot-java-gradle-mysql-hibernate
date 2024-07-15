package com.shylu.spring_practice.controller;

import com.shylu.spring_practice.common.StatusResponse;
import com.shylu.spring_practice.dto.UserDTO;
import com.shylu.spring_practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public UserDTO login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @PutMapping
    public StatusResponse update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping
    public StatusResponse delete(@RequestBody UserDTO userDTO) {
        return userService.delete(userDTO);
    }

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }
}
