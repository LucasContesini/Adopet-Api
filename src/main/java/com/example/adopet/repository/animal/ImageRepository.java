package com.example.adopet.repository.animal;

import com.example.adopet.model.animal.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
