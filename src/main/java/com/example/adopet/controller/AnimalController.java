package com.example.adopet.controller;

import com.example.adopet.dto.CreateAnimalDTO;
import com.example.adopet.dto.animal.AnimalInfoDTO;
import com.example.adopet.dto.animal.AnimalListDTO;
import com.example.adopet.dto.animal.AnimalListOwnerDTO;
import com.example.adopet.dto.animal.FollowDTO;
import com.example.adopet.dto.validation.ExceptionInfoDTO;
import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.AnimalType;
import com.example.adopet.model.animal.Follow;
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

    @PutMapping("/{animalId}")
    public ResponseEntity adoptAnimal(@PathVariable int animalId) {
        try {
            Animal animal = animalService.adoptAnimal(animalId);
            return ResponseEntity.ok(animal);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionInfoDTO(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity deleteAnimal(@PathVariable int animalId) {
        try {
            animalService.deleteAnimal(animalId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @PostMapping("/follow/like")
    public ResponseEntity likeAnimal(@RequestBody FollowDTO followDTO) {
        try {
            Follow follow = animalService.likeAnimal(followDTO.getUserId(), followDTO.getAnimalId(), followDTO.isLike());
            return ResponseEntity.ok(follow);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @PostMapping("/follow/love")
    public ResponseEntity loveAnimal(@RequestBody FollowDTO followDTO) {
        try {
            Follow follow = animalService.loveAnimal(followDTO.getUserId(), followDTO.getAnimalId(), followDTO.isLove());
            return ResponseEntity.ok(follow);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @GetMapping
    public ResponseEntity findAllAnimal(@RequestParam int id) {
        try {
            List<AnimalListDTO> allAnimal = animalService.findAllAnimal(id);
            return ResponseEntity.ok(allAnimal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @GetMapping("/owner")
    public ResponseEntity findAllOwnedAnimal(HttpServletRequest httpServletRequest) {
        try {
            List<AnimalListOwnerDTO> allAnimal = animalService.findAllOwnedAnimal(httpServletRequest);
            return ResponseEntity.ok(allAnimal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable int id, HttpServletRequest httpServletRequest) {
        try {
            AnimalInfoDTO animal = animalService.findById(id, httpServletRequest);
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionInfoDTO(e));
        }
    }
}
