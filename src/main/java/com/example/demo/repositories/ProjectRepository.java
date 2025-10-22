package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Project;
import com.example.demo.models.dto.ProjectDto;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("""
                SELECT
                    new com.example.demo.models.dto.ProjectDto(p.id ,p.name ,p.description ,p.additionaly_file, pt.name)
                FROM
                    Project p JOIN p.projectTypes pt
                WHERE
                    p.id = ?1

            """)
    public ProjectDto getDataById(Integer id);

    @Query("""
                SELECT
                    new com.example.demo.models.dto.ProjectDto(p.id ,p.name ,p.description ,p.additionaly_file ,pt.name)
                FROM
                     Project p JOIN p.projectTypes pt JOIN p.candidateEmployee candidate JOIN candidate.user u
                WHERE
                    u.username = ?1
            """)
    public List<ProjectDto> getDataByCandidateId(String username);
}
