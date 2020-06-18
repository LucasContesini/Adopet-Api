package com.example.adopet.service.auth;

import com.example.adopet.dto.UserDTO;
import com.example.adopet.model.auth.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService {
    UserDTO createUser(User user);
    UserDTO getOwnInfo(HttpServletRequest httpServletRequest);
    User getUserInfo(HttpServletRequest httpServletRequest);
}
