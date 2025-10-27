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
                new com.example.demo.models.dto.ApprovalDto(a.id,a.status ,a.note)
            FROM
                Approval a
            WHERE
                a.status = 'Need Approval'
                """)
    public List<ApprovalDto> getAllData();
}
