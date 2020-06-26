package com.example.adopet.repository.animal;

import com.example.adopet.model.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllByAdoptedFalse();
}
