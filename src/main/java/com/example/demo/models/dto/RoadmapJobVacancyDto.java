package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapJobVacancyDto {
    private Integer id;
    private String action;
    private String feedback;
    private String submit_date;
    private Integer candidateEmployee;
    private Integer jobVacancy;
}
