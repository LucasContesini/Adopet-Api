package com.example.adopet.service.animal;

import com.example.adopet.dto.CreateAnimalDTO;
import com.example.adopet.dto.animal.AnimalInfoDTO;
import com.example.adopet.dto.animal.AnimalListDTO;
import com.example.adopet.dto.animal.AnimalListOwnerDTO;
import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.AnimalType;
import com.example.adopet.model.animal.Follow;
import com.example.adopet.util.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface AnimalService {
    List<AnimalType> findAllAnimalType();
    Animal saveAnimal(CreateAnimalDTO animalDTO, HttpServletRequest httpServletRequest) throws DataNotFoundException;
    Animal updateAnimal(int id, CreateAnimalDTO animalDTO, HttpServletRequest httpServletRequest) throws DataNotFoundException;
    List<AnimalListDTO> findAllAnimal(int id, String city, Integer type, boolean vaccinated, boolean castrated);
    List<AnimalListDTO> findAllInterestedAnimal(HttpServletRequest httpServletRequest);
    List<AnimalListDTO> findAllByUserIdAndAdoptedTrue(HttpServletRequest httpServletRequest);
    List<AnimalListOwnerDTO> findAllOwnedAnimal(HttpServletRequest httpServletRequest);
    AnimalInfoDTO findById(int id, int userId);
    Follow likeAnimal(int userId, int animalId, boolean isLiked);
    Follow loveAnimal(int userId, int animalId, boolean isLoved);

    Animal adoptAnimal(int animalId) throws DataNotFoundException;

    void deleteAnimal(int animalId) throws DataNotFoundException;
}
