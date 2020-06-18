package com.example.adopet.repository.auth;

import com.example.adopet.model.auth.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Perfil findByName(String name);
}
