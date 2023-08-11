package com.demo.springgraphql2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController {

    @GetMapping(path = "/health-check")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("This API is healthy!", HttpStatus.OK);
    }

}
