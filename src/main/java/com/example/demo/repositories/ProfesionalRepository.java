package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Profesional;
import com.example.demo.models.dto.ProfesionalDto;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {
    @Query("""
                SELECT
                    new com.example.demo.models.dto.ProfesionalDto(p.id ,p.name ,p.description ,p.start_date ,p.finish_date ,p.location,p.additionaly_file ,pt.name , c.id , pt.id)
                FROM
                    Profesional p JOIN p.profesionalTypes pt JOIN p.candidateEmployee c
                WHERE
                    p.id = ?1

            """)
    public ProfesionalDto getDataById(Integer id);

    @Query("""
                SELECT
                    new com.example.demo.models.dto.ProfesionalDto(p.id ,p.name ,p.description ,p.start_date ,p.finish_date ,p.location,p.additionaly_file ,pt.name , c.id , pt.id)
                FROM
                    Profesional p JOIN p.profesionalTypes pt JOIN p.candidateEmployee c JOIN c.user u
                WHERE
                    u.id = ?1
            """)
    public List<ProfesionalDto> getDataByCandidateId(Integer id);
}
