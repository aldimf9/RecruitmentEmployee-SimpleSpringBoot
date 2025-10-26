package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String birthDate;
    private String cityDate;
    private String curiculumVitae;
    private String portofolio;
    private String username;
    private String password;
    private Integer role;
}
