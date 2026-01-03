package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CertificationType;
import com.example.demo.models.dto.CertificationTypeDto;

@Repository
public interface CertificationTypeRepository extends JpaRepository<CertificationType, Integer> {
    @Query("""
        SELECT
            new com.example.demo.models.dto.CertificationTypeDto(t.id,t.name)
        FROM
            CertificationType t     
    """)
    public List<CertificationTypeDto> getCertificationType(); 
}
