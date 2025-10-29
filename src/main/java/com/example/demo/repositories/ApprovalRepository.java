package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Approval;
import com.example.demo.models.dto.ApprovalDto;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
    @Query("""
            SELECT
                new com.example.demo.models.dto.ApprovalDto(a.id,a.status,j.name,c.firstName,c.lastName)
            FROM
                Approval a JOIN a.roadmapJobVacancy rdmp JOIN rdmp.jobVacancy j JOIN rdmp.candidateEmployee c
            WHERE
                a.status = 'Need Approval' AND rdmp.action = ?1
                """)
    public List<ApprovalDto> getAllData(String action);
}
