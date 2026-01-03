package com.example.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificationDto {

    public CertificationDto(Integer id ,String name ,String description,String addtional_file ,String available_start_date ,String available_end_date ,String certificationType,Integer certificationTypeId ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.addtional_file = addtional_file;
        this.available_start_date = available_start_date;
        this.available_end_date = available_end_date;
        this.certificationType = certificationType;
        this.certificationTypeId = certificationTypeId;
    }

    public CertificationDto(Integer id, String name ,String description,String addtional_file ,String available_start_date ,String available_end_date ,Integer candidateEmployee,Integer certificationTypeId ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.addtional_file = addtional_file;
        this.available_start_date = available_start_date;
        this.available_end_date = available_end_date;
        this.candidateEmployee = candidateEmployee;
        this.certificationTypeId = certificationTypeId;
    }

    private Integer id;
    private String name;
    private String description;
    private String addtional_file;
    private String available_start_date;
    private String available_end_date;
    private Integer candidateEmployee;
    private Integer certificationTypeId;
    private String certificationType;
}
