package com.example.adopet.repository.animal;

import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByAnimal(Animal animal);
}
