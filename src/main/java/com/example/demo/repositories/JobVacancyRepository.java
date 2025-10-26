package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.JobVacancy;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.JobVacancyDto;

@Repository
public interface JobVacancyRepository extends JpaRepository<JobVacancy, Integer> {
    @Query("""
             SELECT
                new com.example.demo.models.dto.JobVacancyDto(jb.id,jb.name,jb.description,jb.status)
            FROM
                JobVacancy jb
             """)
    public List<JobVacancyDto> getAllData();

    @Query("""
             SELECT
                new com.example.demo.models.dto.JobVacancyDto(jb.id,jb.name,jb.description,jb.status)
            FROM
                JobVacancy jb
            WHERE
                jb.status = 1
             """)
    public List<JobVacancyDto> getAllDataForUser();

    @Query("""
             SELECT
                new com.example.demo.models.dto.JobVacancyDto(jb.id,jb.name,jb.description,jb.status)
            FROM
                JobVacancy jb
            WHERE
                jb.id = ?1
             """)
    public JobVacancyDto getDataById(Integer Id);

    @Query("""
             SELECT
                new com.example.demo.models.dto.JobVacancyDto(jb.id,jb.name,jb.description,jb.status)
            FROM
                JobVacancy jb
            WHERE
                jb.id = ?1 AND jb.status = 1
             """)
    public JobVacancyDto getDataByIdForUser(Integer Id);
}
