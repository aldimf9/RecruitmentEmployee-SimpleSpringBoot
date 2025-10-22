package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Organization;
import com.example.demo.models.dto.OrganizationDto;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    @Query("""
                SELECT
                    new com.example.demo.models.dto.OrganizationDto(o.id ,o.name ,o.description ,o.start_date ,o.finish_date ,o.location ,o.additionaly_file,ot.name)
                FROM
                    Organization o JOIN o.organizationTypes ot
                WHERE
                    o.id = ?1

            """)
    public OrganizationDto getDataById(Integer id);

    @Query("""
               SELECT
                    new com.example.demo.models.dto.OrganizationDto(o.id ,o.name ,o.description ,o.start_date ,o.finish_date ,o.location ,o.additionaly_file,ot.name)
                FROM
                    Organization o JOIN o.organizationTypes ot JOIN o.candidateEmployee candidate JOIN candidate.user u
                WHERE
                    u.username = ?1
            """)
    public List<OrganizationDto> getDataByCandidateId(String username);
}
