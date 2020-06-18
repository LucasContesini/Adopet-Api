package com.example.adopet.service.animal;

import com.example.adopet.dto.CreateAnimalDTO;
import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.AnimalType;
import com.example.adopet.model.animal.Image;
import com.example.adopet.model.auth.User;
import com.example.adopet.repository.animal.AnimalRepository;
import com.example.adopet.repository.animal.AnimalTypeRepository;
import com.example.adopet.repository.animal.ImageRepository;
import com.example.adopet.service.auth.UserService;
import com.example.adopet.util.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<AnimalType> findAllAnimalType() {
        List<AnimalType> animalTypes = animalTypeRepository.findAll();
        return animalTypes;
    }

    @Override
    public Animal saveAnimal(CreateAnimalDTO animalDTO, HttpServletRequest httpServletRequest) throws DataNotFoundException {
        User user = userService.getUserInfo(httpServletRequest);
        if (user == null) {
            throw new DataNotFoundException(DataNotFoundException.USER_NOT_FOUND);
        }
        Optional<AnimalType> animalType = animalTypeRepository.findById(animalDTO.getTypeId());
        Animal animal = new Animal();

        animal.setUser(user);
        animalType.ifPresent(animal::setAnimalType);
        animal.setName(animalDTO.getName());
        animal.setBreed(animalDTO.getBreed());
        animal.setBirthDate(animalDTO.getBirthDate());
        animal.setCastrated(animalDTO.isCastrated());
        animal.setVaccinated(animalDTO.isVaccinated());
        animal.setDescription(animalDTO.getDescription());
        animal.setZipCode(animalDTO.getZipCode());

        Animal animalSaved = animalRepository.save(animal);

        List<Image> images = new ArrayList<>();
        animalDTO.getImages().forEach(image -> {
            Image newImage = new Image();
            newImage.setAnimal(animalSaved);
            newImage.setUrl(image);
            images.add(newImage);
        });
        List<Image> savedImages = imageRepository.saveAll(images);
        animalSaved.setImages(savedImages);
        return animalSaved;
    }
}
