package com.example.demo.models.dto;

import java.time.LocalDateTime;

import com.example.demo.models.enums.InterviewStatus;
import com.example.demo.models.enums.InterviewType;
import com.example.demo.models.enums.PhaseRecruitment;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InterviewScheduleDto {

    public InterviewScheduleDto(Integer id,InterviewStatus status){
        this.id = id;
        this.interviewStatus = status;
    }

    public InterviewScheduleDto(String interviewType , String interviewDate, String location ,Integer interviewer, String phase){
        this.interviewType = InterviewType.valueOf(interviewType);
        this.interviewDate = LocalDateTime.parse(interviewDate);
        this.location = location;
        this.candidate = interviewer;
        this.phase = PhaseRecruitment.valueOf(phase);
    }

    public InterviewScheduleDto(Integer id,Integer rdmp,Integer candidate,String jobName,String firstName,String lastName,InterviewType interviewType,LocalDateTime interviewDate,String location,InterviewStatus interviewStatus,LocalDateTime createdAt){
        this.id = id;
        this.rdmp = rdmp;
        this.candidate = candidate;
        this.jobName = jobName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.interviewType = interviewType;
        this.interviewDate = interviewDate;
        this.location = location;
        this.interviewStatus = interviewStatus;
        this.createdAt = createdAt;
    }

    private Integer id;
    private Integer rdmp;
    private Integer candidate;
    private String jobName;
    private String firstName;
    private String lastName;
    private InterviewType interviewType;
    private LocalDateTime interviewDate;
    private String location;
    private InterviewStatus interviewStatus;
    private PhaseRecruitment phase;
    private LocalDateTime createdAt;
    private Integer interviewer;
    // private LocalDateTime updatedAt;
}
