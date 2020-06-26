package com.example.adopet.dto.animal;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class AnimalInfoDTO implements Serializable {
    private int id;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private String type;
    private String description;
    private boolean vaccinated;
    private boolean castrated;
    private String url;
    private boolean liked;
    private boolean loved;
}