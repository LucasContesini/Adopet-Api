package com.example.adopet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity healthCheck() {
        return ResponseEntity.ok().build();
    }
}
