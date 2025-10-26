package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "approval")

@AllArgsConstructor
@NoArgsConstructor
public class Approval {
    @Id
    @Column(name="id")
    private Integer id;
    private String status;
    private String note;
    private String approvalDate;
    private String createdAt;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private RoadmapJobVacancy roadmapJobVacancy;

    @ManyToOne
    @JoinColumn(name="user",referencedColumnName="id")
    private User user;
    
}
