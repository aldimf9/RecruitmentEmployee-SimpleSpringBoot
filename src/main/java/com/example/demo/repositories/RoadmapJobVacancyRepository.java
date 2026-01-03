package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.JobVacancyDto;
import com.example.demo.models.dto.RoadmapJobVacancyDto;

@Repository
public interface RoadmapJobVacancyRepository extends JpaRepository<RoadmapJobVacancy, Integer> {
    @Query("""
            SELECT
                new com.example.demo.models.dto.JobVacancyDto(job.id,job.name,job.description,rdmp.feedback)
            FROM
                RoadmapJobVacancy rdmp JOIN rdmp.candidateEmployee candidate JOIN rdmp.jobVacancy job JOIN candidate.user user
            WHERE
                user.username = ?1 AND rdmp.action LIKE 'APP%' AND job.status = 1
            """)
    public List<JobVacancyDto> getApplyByUser(String username);

    @Query("""
            SELECT
                new com.example.demo.models.dto.RoadmapJobVacancyDto(rdmp.id,rdmp.action,rdmp.feedback,rdmp.submit_date)
            FROM
                RoadmapJobVacancy rdmp JOIN rdmp.candidateEmployee candidate JOIN rdmp.jobVacancy job JOIN candidate.user user
            WHERE
                user.username = ?1 AND job.id = ?2
            """)
    public List<RoadmapJobVacancyDto> getApplyDetailForUser(String username, Integer job_id);

    @Query("""
            SELECT
                new com.example.demo.models.dto.CandidateEmployeeDto(rdmp.id ,c.firstName ,c.lastName ,c.address ,c.phoneNumber ,c.birth_date ,c.city_date ,c.curiculumVitae ,c.portofolio)
            FROM
                RoadmapJobVacancy rdmp JOIN rdmp.candidateEmployee c JOIN rdmp.jobVacancy job
            WHERE
                rdmp.action LIKE 'APP%' AND job.id = ?1
            """)
    public List<CandidateEmployeeDto> getCandidateApplyById(Integer id);

    @Query("""
            SELECT
                new com.example.demo.models.dto.RoadmapJobVacancyDto(rdmp.id,rdmp.action,c.firstName,c.lastName,j.name,u.username)
            FROM
                RoadmapJobVacancy rdmp JOIN rdmp.candidateEmployee c JOIN rdmp.jobVacancy j JOIN c.user u
            WHERE
                rdmp.action = 'APPLY'
            """)
    public List<RoadmapJobVacancyDto> getAllDataNewApply();
}
