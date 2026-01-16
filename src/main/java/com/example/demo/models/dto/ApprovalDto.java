package com.example.demo.models.dto;

import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.User;
import com.example.demo.models.enums.ApprovalStatus;
import com.example.demo.models.enums.PhaseRecruitment;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApprovalDto {

    public ApprovalDto(ApprovalStatus status, String note) {
        this.status = status;
        this.note = note;
    }

    public ApprovalDto(Integer id, ApprovalStatus status, String job, String firstName, String lastName) {
        this.id = id;
        this.status = status;
        this.job = job;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ApprovalDto(Integer id, ApprovalStatus status, String note, Integer userId) {
        this.id = id;
        this.status = status;
        this.note = note;
        this.userId = userId;
    }

    public ApprovalDto(Integer id){
        this.id = id;
    }

    private Integer id;
    private ApprovalStatus status;
    private String note;
    private String job;
    private String firstName;
    private PhaseRecruitment phase;
    private String lastName;
    private Integer userId;
    private Integer rdmpId;
    private String approvalDate;
    private String createdAt;
    private PhaseRecruitment phaseApproval;
    private RoadmapJobVacancy roadmapJobVacancy;
    private User user;
}
