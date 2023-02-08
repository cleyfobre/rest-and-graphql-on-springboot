package com.example.demo.controller;

import com.example.demo.auth.PrincipalDetails;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
@Slf4j
public class AuthV1Controller {

    private final UserService userService;

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
    public String mypage(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info(principalDetails.getUsers().toString());
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
        log.info("users: " + users);
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
        userService.createUsers(users);
        return "redirect:/login";
    }

    @GetMapping("/login/test")
    public String loginTest(Authentication authentication,
                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        log.info("principal: " + principal.getUsers());
        log.info("principal: " + principalDetails.getUsers());
        return "index";
    }

    @GetMapping("/login/oauth/test")
    public String loginOAuthTest(Authentication authentication,
                                 @AuthenticationPrincipal OAuth2User oAuth2User) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        log.info("principal: " + principal.getAttributes());
        log.info("principal: " + oAuth2User.getAttributes());
        return "index";
    }
}
