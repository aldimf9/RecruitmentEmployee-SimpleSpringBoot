package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.dto.CandidateEmployeeDto;

@Repository
public interface  CandidateEmployeeRepository extends JpaRepository<CandidateEmployee, Integer> {
    @Query("""
        SELECT
            new com.example.demo.models.dto.CandidateEmployeeDto(c.id ,c.firstName ,c.lastName ,c.address ,c.phoneNumber ,c.birth_date ,c.city_date ,c.curiculumVitae ,c.portofolio)
        FROM
            CandidateEmployee c
    """)
    public List<CandidateEmployeeDto> getAllData();

    @Query("""
        SELECT
            new com.example.demo.models.dto.CandidateEmployeeDto(c.id ,c.firstName ,c.lastName ,c.address ,c.phoneNumber ,c.birth_date ,c.city_date ,c.curiculumVitae ,c.portofolio)
        FROM
            CandidateEmployee c
        WHERE
            c.id = ?1
    """)
    public CandidateEmployeeDto getDataById(Integer id);

    @Query("""
        SELECT
            new com.example.demo.models.dto.CandidateEmployeeDto(c.id ,c.firstName ,c.lastName ,c.address ,c.phoneNumber ,c.birth_date ,c.city_date ,c.curiculumVitae ,c.portofolio)
        FROM
            CandidateEmployee c
        WHERE
            c.firstName = ?1 AND c.lastName = ?2
            """)
    public CandidateEmployeeDto getId(String firstName,String lastName);

    Boolean existsByFirstName(String firstName);
    Boolean existsByLastName(String lastName);  
}
