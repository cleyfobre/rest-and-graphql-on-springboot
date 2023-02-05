package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public Users createUsers(@RequestBody Users users) {
        return userService.createUsers(users);
    }

    @GetMapping("/users")
    public List<Users> getUsers(@RequestParam(required = false, defaultValue = "") String name) {
        return userService.getUsers(name);
    }

    @GetMapping("/user")
    public Users getUser(@RequestParam String name) {
        return userService.getUser(name);
    }

}
