package com.example.demo.controller;

import com.example.demo.auth.PrincipalDetails;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthV2Controller {

    @GetMapping("/v2/test")
    public String test() {
        return "test";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/v2/admin")
    public String admin() {
        return "admin";
    }

    @Secured("ROLE_USER")
    @GetMapping("/v2/user")
    public String user(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return "user";
    }

}
