package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="job_vacancy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobVacancy {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private boolean status;

    @OneToMany(mappedBy="jobVacancy",fetch=FetchType.LAZY)
    public List<RoadmapJobVacancy> roadmapJobVacancy;
}
