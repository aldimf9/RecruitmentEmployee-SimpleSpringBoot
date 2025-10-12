package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="profesional")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesional {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String start_date;
    private String finish_date;
    private String location;
    private String description;
    private String additionaly_file;

    @ManyToOne
    @JoinColumn(name="candidate_employee", referencedColumnName="id")
    private CandidateEmployee candidateEmployee;

    @ManyToOne
    @JoinColumn(name="profesional_types",referencedColumnName="id")
    private ProfesionalTypes profesionalTypes;
}
