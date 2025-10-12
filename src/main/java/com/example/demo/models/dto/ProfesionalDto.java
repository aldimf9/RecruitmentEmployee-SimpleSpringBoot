package com.example.demo.models.dto;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.ProfesionalTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalDto {
    private Integer id;
    private String name;
    private String start_date;
    private String finish_date;
    private String location;
    private String description;
    private String additionaly_file;
    private CandidateEmployee candidateEmployee;
    private ProfesionalTypes profesionalTypes;
}
