package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapJobVacancyDto {

    public RoadmapJobVacancyDto(Integer id, String action, String feedback, String submit_date) {
        this.id = id;
        this.action = action;
        this.feedback = feedback;
        this.submit_date = submit_date;
    }

    private Integer id;
    private String action;
    private String feedback;
    private String submit_date;
    private Integer candidateEmployee;
    private Integer jobVacancy;
}
