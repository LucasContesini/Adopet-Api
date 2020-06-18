package com.example.adopet.model.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "animal_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalType {

    @JsonIgnore
    @OneToMany(mappedBy = "animalType")
    List<Animal> animals;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
