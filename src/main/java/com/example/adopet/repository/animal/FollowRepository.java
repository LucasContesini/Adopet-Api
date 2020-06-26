package com.example.adopet.repository.animal;

import com.example.adopet.model.animal.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    @Query("SELECT f FROM Follow f WHERE f.user.id = :userId AND f.animal.id = :animalId")
    Follow findByUserIdAndAnimalId(int userId, int animalId);
}
