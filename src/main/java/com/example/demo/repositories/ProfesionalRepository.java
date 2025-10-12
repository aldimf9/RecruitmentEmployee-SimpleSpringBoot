package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Profesional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
    
}
