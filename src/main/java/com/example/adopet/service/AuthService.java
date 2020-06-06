package com.example.adopet.service;

import com.example.adopet.dto.auth.LoginDTO;
import com.example.adopet.dto.auth.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    TokenDTO login(LoginDTO loginDTO);
}
