package com.example.demo.models.dto;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.models.enums.PhaseRecruitment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEmployeeDto {

    public CandidateEmployeeDto(Integer id, String firstName, String lastName, String address, String phoneNumber,
            String birth_date, String city_date, String curiculumVitae, String portofolio,String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birth_date = birth_date;
        this.city_date = city_date;
        this.curiculumVitae = curiculumVitae;
        this.portofolio = portofolio;
        this.username = username;
    }

    public CandidateEmployeeDto(Integer id, String firstName, String lastName, String address, String phoneNumber,
            String birth_date, String city_date, String curiculumVitae, String portofolio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birth_date = birth_date;
        this.city_date = city_date;
        this.curiculumVitae = curiculumVitae;
        this.portofolio = portofolio;
    }

    public CandidateEmployeeDto(Integer id, String job, PhaseRecruitment action , String submit_date) {
        this.id = id;
        this.job = job;
        this.action = action;
        this.submit_date = submit_date;
    }

    public CandidateEmployeeDto(Integer id, String job, List<RoadmapJobVacancyDto> roadmap) {
        this.id = id;
        this.job = job;
        this.roadmap = roadmap;
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String birth_date;
    private String city_date;
    private String curiculumVitae;
    private String portofolio;
    private String submit_date;
    private String job;
    @Enumerated(EnumType.STRING)
    private PhaseRecruitment action;
    private List<RoadmapJobVacancyDto> roadmap;
    private String username;
}
