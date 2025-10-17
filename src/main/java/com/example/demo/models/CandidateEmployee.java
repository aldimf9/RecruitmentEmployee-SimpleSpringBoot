package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "candidate_employee")

@AllArgsConstructor
@NoArgsConstructor
public class CandidateEmployee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String birth_date;
    private String city_date;
    private String curiculumVitae;
    private String portofolio;

    @OneToOne(mappedBy="candidateEmployee",cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToMany(mappedBy="candidateEmployee",fetch=FetchType.LAZY)
    public List<Certification> certification;

    @OneToMany(mappedBy="candidateEmployee",fetch=FetchType.LAZY)
    public List<Project> project;

    @OneToMany(mappedBy="candidateEmployee",fetch=FetchType.LAZY)
    public List<Profesional> profesional;

    @OneToMany(mappedBy="candidateEmployee",fetch=FetchType.LAZY)
    public List<Organization> organization;

    @OneToMany(mappedBy="candidateEmployee",fetch=FetchType.LAZY)
    public List<RoadmapJobVacancy> roadmapJobVacancy;
}
