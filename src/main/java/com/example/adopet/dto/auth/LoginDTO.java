package com.example.adopet.dto.auth;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LoginDTO implements Serializable {
    private String password;
    private String email;
}
