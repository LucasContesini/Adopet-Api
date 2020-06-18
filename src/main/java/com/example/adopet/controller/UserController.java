package com.example.adopet.controller;

import com.example.adopet.dto.UserDTO;
import com.example.adopet.dto.validation.ExceptionInfoDTO;
import com.example.adopet.model.auth.User;
import com.example.adopet.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            UserDTO savedUser = userService.createUser(user);
            if (savedUser == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @GetMapping
    public ResponseEntity getOwnInfo(HttpServletRequest httpServletRequest) {
        try {
            UserDTO ownInfo = userService.getOwnInfo(httpServletRequest);
            return ResponseEntity.ok(ownInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }
}
