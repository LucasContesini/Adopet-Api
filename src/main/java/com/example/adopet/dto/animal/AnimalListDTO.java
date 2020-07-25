package com.example.adopet.dto.animal;

import com.example.adopet.dto.UserDTO;
import com.example.adopet.model.auth.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AnimalListDTO implements Serializable {
    private int id;
    private String name;
    private boolean vaccinated;
    private boolean castrated;
    private String image;
    private boolean liked;
    private boolean loved;
    private UserDTO user;
}
