package com.example.demo.models;

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
@Table(name="organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String start_date;
    private String finish_date;
    private String location;
    private String additionaly_file;

    @ManyToOne
    @JoinColumn(name="candidate_employee",referencedColumnName="id")
    private CandidateEmployee candidateEmployee;

    @ManyToOne
    @JoinColumn(name="organization_type",referencedColumnName="id")
    private OrganizationTypes organizationTypes;
}
