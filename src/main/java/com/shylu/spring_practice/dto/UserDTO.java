package com.shylu.spring_practice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String uuid;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private Set<String> roles;
}
