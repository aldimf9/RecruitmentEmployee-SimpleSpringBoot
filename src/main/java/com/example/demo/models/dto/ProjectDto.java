package com.example.demo.models.dto;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.ProjectTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Integer id;
    private String name;
    private String description;
    private String additionaly_file;
    private Integer candidateEmployee;
    private Integer projectType;
}
