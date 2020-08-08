package com.example.adopet.repository.animal;

import com.example.adopet.model.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllByAdoptedFalse();

    @Query("SELECT a FROM Animal a JOIN Follow f ON a.id = f.animal.id WHERE f.user.id = :userId AND (f.liked is true or f.loved is true)")
    List<Animal> findAllByLikedOrLoved(int userId);

    List<Animal> findAllByUserIdAndAdoptedTrue(int userId);
}
