package com.example.adopet.repository.animal;

import com.example.adopet.model.animal.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
}
