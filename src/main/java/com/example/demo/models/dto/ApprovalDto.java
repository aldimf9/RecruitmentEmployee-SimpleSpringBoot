package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDto {

    public ApprovalDto(String status,String note){
        this.status = status;
        this.note = note;
    }
    public ApprovalDto(Integer id,String status,String job,String firstName,String lastName){
        this.id = id;
        this.status = status;
        this.job = job;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ApprovalDto(Integer id,String status,String note,Integer userId){
        this.id = id;
        this.status = status;
        this.note = note;
        this.userId = userId;
    }

    private Integer id;
    private String status;
    private String note;
    private String job;
    private String firstName;
    private String lastName;
    private Integer userId;
}
