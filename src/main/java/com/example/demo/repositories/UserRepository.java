package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // for edit and insert
    @Query("""
                SELECT
                    new com.example.demo.models.dto.UserDto(u.id, u.username, u.password ,r.id)
                FROM
                    User u JOIN u.role r
                WHERE
                    u.id = ?1
            """)
    public UserDto getWithId(Integer id);

    @Query("""
                SELECT
                    new com.example.demo.models.dto.UserDto(u.id, u.username, u.password ,r.id ,r.name, c.firstName, c.lastName)
                FROM
                    User u JOIN u.role r JOIN u.candidateEmployee c
            """)
    public List<UserDto> getAllData();

    @Query("""
                SELECT
                    new com.example.demo.models.dto.UserDto(u.username, u.password, r.name)
                FROM
                    User u JOIN u.role r
                WHERE
                    u.username = ?1
            """)
    public UserDto findByUsername(String username);
    
    boolean existsByUsername(String username);

}
