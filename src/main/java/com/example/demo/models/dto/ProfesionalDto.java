package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalDto {

    public ProfesionalDto(Integer id, String name, String description, String start_date, String finish_date,
            String location, String additionaly_file, String profesionalType, Integer candidateEmployee,
            Integer profesionalTypeId) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.location = location;
        this.description = description;
        this.additionaly_file = additionaly_file;
        this.profesionalType = profesionalType;
        this.candidateEmployee = candidateEmployee;
        this.profesionalTypeId = profesionalTypeId;
    }

    public ProfesionalDto(Integer id, String name, String description, String start_date, String finish_date,
            String location, String additionaly_file, Integer candidateEmployee,
            Integer profesionalTypeId) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.location = location;
        this.description = description;
        this.additionaly_file = additionaly_file;
        this.candidateEmployee = candidateEmployee;
        this.profesionalTypeId = profesionalTypeId;
    }

    private Integer id;
    private String name;
    private String start_date;
    private String finish_date;
    private String location;
    private String description;
    private String additionaly_file;
    private Integer candidateEmployee;
    private Integer profesionalTypeId;
    private String profesionalType;

}
