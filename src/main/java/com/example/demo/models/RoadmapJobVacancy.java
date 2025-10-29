package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roadmap_job_vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapJobVacancy {

    public RoadmapJobVacancy(Integer id, String action, String feedback, String submit_date,
            CandidateEmployee candidateEmployee, JobVacancy jobVacancy) {
        this.id = id;
        this.action = action;
        this.feedback = feedback;
        this.submit_date = submit_date;
        this.candidateEmployee = candidateEmployee;
        this.jobVacancy = jobVacancy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String action;
    private String feedback;
    private String submit_date;

    @ManyToOne
    @JoinColumn(name = "candidate_employee", referencedColumnName = "id")
    private CandidateEmployee candidateEmployee;

    @ManyToOne
    @JoinColumn(name = "job_vacancy", referencedColumnName = "id")
    private JobVacancy jobVacancy;

    @OneToOne(mappedBy = "roadmapJobVacancy", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Approval approval;
}
