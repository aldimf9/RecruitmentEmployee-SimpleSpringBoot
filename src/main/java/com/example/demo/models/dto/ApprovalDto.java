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

    private Integer id;
    private String status;
    private String note;
    private Integer userId;
}
