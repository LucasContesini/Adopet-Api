package com.example.adopet.dto.animal;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AnimalListOwnerDTO implements Serializable {
    private int id;
    private String name;
    private String image;
    private boolean adopted;
}
