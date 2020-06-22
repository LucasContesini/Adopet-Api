package com.example.adopet.service.animal;

import com.example.adopet.dto.CreateAnimalDTO;
import com.example.adopet.dto.animal.AnimalListDTO;
import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.AnimalType;
import com.example.adopet.util.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface AnimalService {
    List<AnimalType> findAllAnimalType();
    Animal saveAnimal(CreateAnimalDTO animalDTO, HttpServletRequest httpServletRequest) throws DataNotFoundException;
    List<AnimalListDTO> findAllAnimal();
}
