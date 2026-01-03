package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Certification;
import com.example.demo.models.dto.CertificationDto;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer> {
    @Query("""
                SELECT
                    new com.example.demo.models.dto.CertificationDto(c.id ,c.name ,c.description ,c.additionaly_file ,c.available_start_date ,c.available_end_date ,ct.name , ct.id)
                FROM
                    Certification c JOIN c.certificationTypes ct
                WHERE
                    c.id = ?1

            """)
    public CertificationDto getCertificationDataById(Integer id);

    @Query("""
                SELECT
                    new com.example.demo.models.dto.CertificationDto(c.id ,c.name ,c.description ,c.additionaly_file ,c.available_start_date ,c.available_end_date ,ct.name, ct.id)
                FROM
                    Certification c JOIN c.certificationTypes ct JOIN c.candidateEmployee candidate JOIN candidate.user u
                WHERE
                    u.id = ?1
            """)
    public List<CertificationDto> getAllCertificationDataByCandidateId(Integer id);
}
