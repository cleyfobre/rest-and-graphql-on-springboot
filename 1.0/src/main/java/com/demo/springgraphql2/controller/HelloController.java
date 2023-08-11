package com.demo.springgraphql2.controller;

import com.demo.springgraphql2.dto.AuthDto;
import com.demo.springgraphql2.entity.Target;
import com.demo.springgraphql2.service.TargetService;
import com.demo.springgraphql2.service.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private TargetService targetService;

    @PostMapping(path = "/login")
    public ResponseEntity<AuthDto.JwtTokenResponse> login(@RequestBody AuthDto.LoginRequest request) {
        AuthDto.JwtTokenResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @QueryMapping
    public Target getTarget(@Argument Long id) {
        return targetService.getTarget(id);
    }

}
