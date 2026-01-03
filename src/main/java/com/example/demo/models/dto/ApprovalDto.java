package com.example.demo.models.dto;

import com.example.demo.models.enums.ApprovalStatus;
import com.example.demo.models.enums.PhaseRecruitment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDto {

    public ApprovalDto(ApprovalStatus status,String note){
        this.status = status;
        this.note = note;
    }
    public ApprovalDto(Integer id,ApprovalStatus status,String job,String firstName,String lastName){
        this.id = id;
        this.status = status;
        this.job = job;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ApprovalDto(Integer id,ApprovalStatus status,String note,Integer userId){
        this.id = id;
        this.status = status;
        this.note = note;
        this.userId = userId;
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
}
