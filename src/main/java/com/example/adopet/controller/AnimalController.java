package com.example.adopet.controller;

import com.example.adopet.dto.CreateAnimalDTO;
import com.example.adopet.dto.validation.ExceptionInfoDTO;
import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.AnimalType;
import com.example.adopet.service.animal.AnimalService;
import com.example.adopet.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("type")
    public ResponseEntity findAllAnimalType() {
        try {
            List<AnimalType> allAnimalType = animalService.findAllAnimalType();
            return ResponseEntity.ok(allAnimalType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping
    public ResponseEntity saveAnimal(@RequestBody CreateAnimalDTO createAnimalDTO, HttpServletRequest httpServletRequest) {
        try {
            Animal animal = animalService.saveAnimal(createAnimalDTO, httpServletRequest);
            return ResponseEntity.ok(animal);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionInfoDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }
}
