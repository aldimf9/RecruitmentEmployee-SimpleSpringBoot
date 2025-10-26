package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;
import com.example.demo.models.dto.RoleDto;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("""
        SELECT
            new com.example.demo.models.dto.RoleDto(r.id,r.name)
        FROM
            Role r
    """)
    public List<RoleDto> getAllData();

    @Query("""
        SELECT
            new com.example.demo.models.dto.RoleDto(r.id,r.name)
        FROM
            Role r
        WHERE
            r.id = ?1
    """)
    public RoleDto getRoleById(Integer id);
}
