package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.RoadmapJobVacancy;

@Repository
public interface RoadmapJobVacancyRepository extends JpaRepository<RoadmapJobVacancy, Integer> {
    
}
