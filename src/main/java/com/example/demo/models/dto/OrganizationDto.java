package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private Integer id;
    private String name;
    private String description;
    private String start_date;
    private String finish_date;
    private String location;
    private String additionaly_file;
    private Integer candidateEmployee;
    private Integer organizationType;
}
