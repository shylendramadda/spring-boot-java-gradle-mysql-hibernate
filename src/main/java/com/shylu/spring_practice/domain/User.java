package com.shylu.spring_practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobile;
    @Column(name = "role")
    private Set<String> roles;
    private boolean isEnabled;
}
