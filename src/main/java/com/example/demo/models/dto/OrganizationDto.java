package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {

    public OrganizationDto(Integer id,String name,String description,String start_date,String finish_date,String location,String additionaly_file,String organizationType){
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.location = location;
        this.additionaly_file = additionaly_file;
        this.organizationType = organizationType;
    }

    private Integer id;
    private String name;
    private String description;
    private String start_date;
    private String finish_date;
    private String location;
    private String additionaly_file;
    private Integer candidateEmployee;
    private Integer organizationTypeId;
    private String organizationType;
}
