package com.example.demo.controller;

import com.example.demo.auth.PrincipalDetails;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @Secured("ROLE_USER")
    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/sales")
    public String sales() {
        return "index";
    }

    @GetMapping("/test")
    public String test(Authentication authentication) {
        Users users = ((PrincipalDetails) authentication.getPrincipal()).getUsers();
        System.out.println("users: " + users);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "signupForm";
    }

    @PostMapping("/signup.do")
    public String signup(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userService.createUsers(users);
        return "redirect:/login";
    }

}
