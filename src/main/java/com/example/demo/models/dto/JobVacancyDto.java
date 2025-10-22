package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobVacancyDto {

    public JobVacancyDto(Integer id,String name,String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    private Integer id;
    private String name;
    private String description;
    private Boolean status;
} 
