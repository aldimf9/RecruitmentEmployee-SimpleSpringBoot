package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProjectTypes;

@Repository
public interface ProjectTypesRepository extends JpaRepository<ProjectTypes, Integer> {
    
}
