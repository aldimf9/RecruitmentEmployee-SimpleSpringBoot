package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProjectType;
import com.example.demo.models.dto.ProjectTypeDto;

@Repository
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Integer> {
    @Query("""
        SELECT
            new com.example.demo.models.dto.ProjectTypeDto(t.id,t.name)
        FROM
            ProjectType t
    """)
    public List<ProjectTypeDto> getProjectTypeData();
}
