package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    public ProjectDto(Integer id, String name, String description, String additionaly_file, String projectType,
            Integer candidateEmployee,
            Integer projectTypeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.additionaly_file = additionaly_file;
        this.projectType = projectType;
        this.candidateEmployee = candidateEmployee;
        this.projectTypeId = projectTypeId;
    }

    public ProjectDto(Integer id, String name, String description, String additionaly_file, Integer candidateEmployee,
            Integer projectTypeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.additionaly_file = additionaly_file;
        this.candidateEmployee = candidateEmployee;
        this.projectTypeId = projectTypeId;
    }

    private Integer id;
    private String name;
    private String description;
    private String additionaly_file;
    private Integer candidateEmployee;
    private Integer projectTypeId;
    private String projectType;
}
