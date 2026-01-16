package com.example.demo.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.example.demo.models.enums.InterviewStatus;
import com.example.demo.models.enums.InterviewType;
import com.example.demo.models.enums.PhaseRecruitment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "interview_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "roadmap_job_vacancy",referencedColumnName = "id")
    private RoadmapJobVacancy roadmap;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "id")
    private User interviewer;

    @Enumerated(EnumType.STRING)
    @Column(name = "interview_type",length = 20)
    private InterviewType interviewType;

    private LocalDateTime interviewDate;

    private String location;

    private PhaseRecruitment phase;

    @Enumerated(EnumType.STRING)
    @Column(name = "interview_status", length = 20)
    private InterviewStatus interviewStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // @UpdateTimestamp
    // private LocalDateTime updatedAt;

}
