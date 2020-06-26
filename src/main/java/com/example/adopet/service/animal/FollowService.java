package com.example.adopet.service.animal;

import com.example.adopet.model.animal.Follow;
import org.springframework.stereotype.Service;

@Service
public interface FollowService {
    Follow findByUserIdAndAnimalId(int userId, int animalId);
    Follow save(Follow follow);
}
