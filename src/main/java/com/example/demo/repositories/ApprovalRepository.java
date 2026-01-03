package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Approval;
import com.example.demo.models.dto.ApprovalDto;
import com.example.demo.models.enums.PhaseRecruitment;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
    @Query("""
            SELECT
                new com.example.demo.models.dto.ApprovalDto(a.id,a.status,j.name,c.firstName,c.lastName)
            FROM
                Approval a JOIN a.roadmapJobVacancy rdmp JOIN rdmp.jobVacancy j JOIN rdmp.candidateEmployee c
            WHERE
                a.status = 'Waiting for Approval' AND rdmp.action = ?1
                """)
    public List<ApprovalDto> getAllData(PhaseRecruitment action);

    @Query("""
            SELECT 
                a
            FROM 
                Approval a 
            WHERE
                a.roadmapJobVacancy.id = ?1
            """)
    public List<Approval> findByRoadmapId(Integer idRoadmap);
}
