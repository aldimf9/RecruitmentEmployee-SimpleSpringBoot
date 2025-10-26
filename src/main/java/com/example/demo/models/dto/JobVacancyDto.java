package com.example.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobVacancyDto {

    public JobVacancyDto(Integer id,String name,String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public JobVacancyDto(Integer id,String name,String description , String status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.statusVacancy = status;
    }

    public JobVacancyDto(Integer id,String name,String description , Boolean status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    private Integer id;
    private String name;
    private String description;
    private Boolean status;
    private String statusVacancy;
} 
