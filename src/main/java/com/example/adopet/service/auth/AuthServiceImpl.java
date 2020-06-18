package com.example.adopet.service.auth;

import com.example.adopet.config.JWTService;
import com.example.adopet.dto.auth.LoginDTO;
import com.example.adopet.dto.auth.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Override
    public TokenDTO login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        Authentication authenticate = authenticationManager.authenticate(login);
        TokenDTO token = new TokenDTO(jwtService.generateToken(authenticate), "bearer");
        return token;
    }
}
