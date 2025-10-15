package com.example.demo.models.dto;

import com.example.demo.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String birth_date;
    private String city_date;
    private String curiculumVitae;
    private String portofolio;
}
