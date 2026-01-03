package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProfesionalType;
import com.example.demo.models.dto.ProfesionalTypeDto;


@Repository
public interface ProfesionalTypeRepository extends JpaRepository<ProfesionalType, Integer> {
    @Query("""
        SELECT
            new com.example.demo.models.dto.ProfesionalTypeDto(t.id,t.name)
        FROM
           ProfesionalType t
    """)
    public List<ProfesionalTypeDto> getProfesionalTypeData();
}
