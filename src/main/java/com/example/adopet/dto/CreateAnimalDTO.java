package com.example.adopet.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateAnimalDTO implements Serializable {

    private String name;
    private int typeId;
    private String breed;
    private LocalDate birthDate;
    private String description;
    private boolean vaccinated;
    private boolean castrated;
    private String city;
    private List<String> images;
}
