package com.example.demo.models.dto;

import com.example.demo.models.enums.PhaseRecruitment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapJobVacancyDto {

    public RoadmapJobVacancyDto(Integer id, PhaseRecruitment action, String feedback, String submit_date) {
        this.id = id;
        this.action = action;
        this.feedback = feedback;
        this.submit_date = submit_date;
    }

    public RoadmapJobVacancyDto(Integer id, PhaseRecruitment action, String firstName, String lastName, String jobName,String username) {
        this.id = id;
        this.action = action;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobName = jobName;
        this.username = username;
    }
   
    public RoadmapJobVacancyDto(PhaseRecruitment action, String submit_date) {
        this.action = action;
        this.submit_date = submit_date;
    }

    private Integer id;
    private PhaseRecruitment action;
    private String feedback;
    private String submit_date;
    private Integer candidateEmployee;
    private Integer jobVacancy;
    private String jobName;
    private String firstName;
    private String lastName;
    private String username;

}
