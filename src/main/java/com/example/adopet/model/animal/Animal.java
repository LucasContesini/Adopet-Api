package com.example.adopet.model.animal;

import com.example.adopet.model.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private String description;
    private boolean vaccinated;
    private boolean castrated;
    private boolean adopted;
    private String zipCode;

    @ManyToOne
    @JsonIgnore
    private User user;
    @ManyToOne
    private AnimalType animalType;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "animal")
    private List<Image> images;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "animal")
    private List<Follow> follows;

}
