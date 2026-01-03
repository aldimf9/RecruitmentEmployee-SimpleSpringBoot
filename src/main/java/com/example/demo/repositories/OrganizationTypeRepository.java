package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.OrganizationType;
import com.example.demo.models.dto.OrganizationTypeDto;

@Repository
public interface OrganizationTypeRepository extends JpaRepository<OrganizationType, Integer> {
    @Query("""
        SELECT
            new com.example.demo.models.dto.OrganizationTypeDto(t.id,t.name)
        FROM
            OrganizationType t
    """)
    public List<OrganizationTypeDto> getOrganizationTypeData();
}
