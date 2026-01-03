package com.example.demo.models;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.models.enums.ApprovalStatus;
import com.example.demo.models.enums.PhaseRecruitment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "approval")

@AllArgsConstructor
@NoArgsConstructor
public class Approval {

    public Approval(ApprovalStatus status, String approvalDate, String createdAt, PhaseRecruitment phaseApproval,
            RoadmapJobVacancy roadmapJobVacancy, User user) {
        this.status = status;
        this.approvalDate = approvalDate;
        this.createdAt = createdAt;
        this.phaseApproval = phaseApproval;
        this.roadmapJobVacancy = roadmapJobVacancy;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;
    private String approvalDate;
    private String createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "phase", length = 20)
    private PhaseRecruitment phaseApproval;

    @ManyToOne
    @JoinColumn(name = "roadmap_id", referencedColumnName = "id")
    private RoadmapJobVacancy roadmapJobVacancy;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

}
