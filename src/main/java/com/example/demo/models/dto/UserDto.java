package com.example.demo.models.dto;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private CandidateEmployee candidateEmployee;
}
