package com.example.adopet.controller;

import com.example.adopet.dto.auth.LoginDTO;
import com.example.adopet.dto.auth.TokenDTO;
import com.example.adopet.dto.validation.ExceptionInfoDTO;
import com.example.adopet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            TokenDTO login = authService.login(loginDTO);
            return ResponseEntity.ok(login);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionInfoDTO(e));
        }
    }
}
