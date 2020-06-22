package com.example.adopet.dto.animal;

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
}
