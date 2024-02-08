package com.example.myspringcrud.repository;

import com.example.myspringcrud.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}

