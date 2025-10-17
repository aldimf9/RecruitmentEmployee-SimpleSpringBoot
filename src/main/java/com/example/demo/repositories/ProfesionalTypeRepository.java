package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProfesionalType;


@Repository
public interface ProfesionalTypeRepository extends JpaRepository<ProfesionalType, Integer> {
    
}
