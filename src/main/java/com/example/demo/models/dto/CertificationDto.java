package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDto {
    private Integer id;
    private String name;
    private String description;
    private String addtional_file;
    private String available_start_date;
    private String available_end_date;
    private Integer candidateEmployee;
    private Integer certificationType;
}
