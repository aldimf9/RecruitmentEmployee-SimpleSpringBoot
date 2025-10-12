package com.example.demo.models.dto;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.JobVacancy;

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
    private CandidateEmployee candidateEmployee;
    private JobVacancy jobVacancy;
}
