package com.example.adopet.service.auth;

import com.example.adopet.config.JWTService;
import com.example.adopet.dto.UserDTO;
import com.example.adopet.model.auth.Perfil;
import com.example.adopet.model.auth.User;
import com.example.adopet.repository.auth.PerfilRepository;
import com.example.adopet.repository.auth.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private JWTService jwtService;

    @Override
    public UserDTO createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            return null;
        user.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setUsername(user.getEmail());
        Perfil perfil = perfilRepository.findByName("USER");
        user.setPerfis(Collections.singletonList(perfil));
        User save = userRepository.save(user);
        UserDTO userDTO = objectMapper.convertValue(save, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO getOwnInfo(HttpServletRequest httpServletRequest) {
        String token = jwtService.getToken(httpServletRequest);
        int userId = jwtService.getUserId(token);
        User user = userRepository.findById(userId);
        UserDTO userDTO = new UserDTO(user.getId(), user.getEmail(), user.getNickname());
        return userDTO;
    }

    public User getUserInfo(HttpServletRequest httpServletRequest) {
        String token = jwtService.getToken(httpServletRequest);
        int userId = jwtService.getUserId(token);
        User user = userRepository.findById(userId);
        return user;
    }
}
