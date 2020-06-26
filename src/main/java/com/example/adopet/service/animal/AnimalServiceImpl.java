package com.example.adopet.service.animal;

import com.example.adopet.dto.CreateAnimalDTO;
import com.example.adopet.dto.UserDTO;
import com.example.adopet.dto.animal.AnimalInfoDTO;
import com.example.adopet.dto.animal.AnimalListDTO;
import com.example.adopet.dto.animal.AnimalListOwnerDTO;
import com.example.adopet.model.animal.Animal;
import com.example.adopet.model.animal.AnimalType;
import com.example.adopet.model.animal.Follow;
import com.example.adopet.model.animal.Image;
import com.example.adopet.model.auth.User;
import com.example.adopet.repository.animal.AnimalRepository;
import com.example.adopet.repository.animal.AnimalTypeRepository;
import com.example.adopet.repository.animal.ImageRepository;
import com.example.adopet.service.auth.UserService;
import com.example.adopet.util.exception.DataNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private FollowService followService;
    @Autowired
    private ObjectMapper objectMapper;

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

    @Override
    public List<AnimalListDTO> findAllAnimal(int id) {
        List<Animal> animals = animalRepository.findAll();
        if (animals.isEmpty())
            return Collections.emptyList();
        List<AnimalListDTO> animalListDTOS = new ArrayList<>();
        animals.forEach(animal -> {
            AnimalListDTO animalListDTO = new AnimalListDTO();
            animalListDTO.setId(animal.getId());
            animalListDTO.setName(animal.getName());
            animalListDTO.setCastrated(animal.isCastrated());
            animalListDTO.setVaccinated(animal.isVaccinated());
            try {
                animalListDTO.setImage(animal.getImages().get(0).getUrl());
            } catch (Exception e) {
                animalListDTO.setImage("");
            }
            Follow follow = animal.getFollows().stream().filter(flw -> flw.getUser().getId() == id).findAny().orElse(null);
            if (follow != null) {
                animalListDTO.setLiked(follow.isLiked());
                animalListDTO.setLoved(follow.isLoved());
            }
            animalListDTOS.add(animalListDTO);
        });
        return animalListDTOS;
    }

    @Override
    public List<AnimalListOwnerDTO> findAllOwnedAnimal(HttpServletRequest httpServletRequest) {
        List<Animal> animals = animalRepository.findAllByAdoptedFalse();
        if (animals.isEmpty())
            return Collections.emptyList();
        UserDTO user = userService.getOwnInfo(httpServletRequest);
        List<Animal> ownerAnimals = animals.stream().filter(animal -> animal.getUser().getId() == user.getId()).collect(Collectors.toList());
        List<AnimalListOwnerDTO> animalListOwnerDTOS = new ArrayList<>();
        ownerAnimals.forEach(animal -> {
            AnimalListOwnerDTO animalListOwnerDTO = new AnimalListOwnerDTO();
            animalListOwnerDTO.setId(animal.getId());
            animalListOwnerDTO.setName(animal.getName());
            animalListOwnerDTO.setAdopted(animal.isAdopted());
            try {
                animalListOwnerDTO.setImage(animal.getImages().get(0).getUrl());
            } catch (IndexOutOfBoundsException e) {
                animalListOwnerDTO.setImage(null);
            }
            animalListOwnerDTOS.add(animalListOwnerDTO);
        });
        return animalListOwnerDTOS;
    }

    @Override
    public AnimalInfoDTO findById(int id, HttpServletRequest httpServletRequest) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (!optionalAnimal.isPresent()) {
            return null;
        }
        Animal animal = optionalAnimal.get();
        AnimalInfoDTO animalInfoDTO = objectMapper.convertValue(animal, AnimalInfoDTO.class);
        try {
            animalInfoDTO.setType(animal.getAnimalType().getName());
        } catch (NullPointerException e) {
            animalInfoDTO.setType("");
        }
        try {
            animalInfoDTO.setUrl(animal.getImages().get(0).getUrl());
        } catch (IndexOutOfBoundsException e) {
            animalInfoDTO.setUrl(null);
        }
        UserDTO user = userService.getOwnInfo(httpServletRequest);
        Follow follow = animal.getFollows().stream().filter(flw -> flw.getUser().getId() == user.getId()).findAny().orElse(null);
        if (follow != null) {
            animalInfoDTO.setLiked(follow.isLiked());
            animalInfoDTO.setLoved(follow.isLoved());
        }
        return animalInfoDTO;
    }

    @Override
    public Follow likeAnimal(int userId, int animalId, boolean isLiked) {
        Follow follow = returnFollow(userId, animalId);
        follow.setLiked(isLiked);
        Follow save = followService.save(follow);
        return save;
    }

    @Override
    public Follow loveAnimal(int userId, int animalId, boolean isLoved) {
        Follow follow = returnFollow(userId, animalId);
        follow.setLoved(isLoved);
        Follow save = followService.save(follow);
        return save;
    }

    @Override
    public Animal adoptAnimal(int animalId) throws DataNotFoundException {
        Optional<Animal> optAnimal = animalRepository.findById(animalId);
        if(!optAnimal.isPresent())
            throw new DataNotFoundException(DataNotFoundException.ANIMAL_NOT_FOUND);
        Animal animal = optAnimal.get();
        animal.setAdopted(true);
        Animal save = animalRepository.save(animal);
        return save;
    }

    @Override
    public void deleteAnimal(int animalId) {
        animalRepository.deleteById(animalId);
    }

    private Follow returnFollow(int userId, int animalId) {
        Follow follow = followService.findByUserIdAndAnimalId(userId, animalId);
        if (follow == null) {
            follow = new Follow();
            User user = userService.getById(userId);
            if (user != null)
                follow.setUser(user);
            Optional<Animal> animal = animalRepository.findById(animalId);
            if (animal.isPresent())
                follow.setAnimal(animal.get());
        }
        return follow;
    }
}
