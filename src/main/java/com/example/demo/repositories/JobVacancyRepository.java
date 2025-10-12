package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.JobVacancy;

@Repository
public interface  JobVacancyRepository extends JpaRepository<JobVacancy, Integer> {
    
}
