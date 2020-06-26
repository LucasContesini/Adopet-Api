package com.example.adopet.service.animal;

import com.example.adopet.model.animal.Follow;
import com.example.adopet.repository.animal.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepository followRepository;

    public Follow findByUserIdAndAnimalId(int userId, int animalId) {
        Follow follow = followRepository.findByUserIdAndAnimalId(userId, animalId);
        return follow;
    }

    @Override
    public Follow save(Follow follow) {
        Follow save = followRepository.save(follow);
        return save;
    }
}
