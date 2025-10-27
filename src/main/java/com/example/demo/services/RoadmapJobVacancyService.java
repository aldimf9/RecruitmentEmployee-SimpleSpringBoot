package com.example.demo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Approval;
import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.JobVacancyDto;
import com.example.demo.models.dto.RoadmapJobVacancyDto;
import com.example.demo.repositories.ApprovalRepository;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.JobVacancyRepository;
import com.example.demo.repositories.RoadmapJobVacancyRepository;

@Service
public class RoadmapJobVacancyService {
    private final RoadmapJobVacancyRepository roadmapJobVacancyRepository;
    private final JobVacancyRepository jobVacancyRepository;
    private final CandidateEmployeeRepository candidateEmployeRepository;
    private final ApprovalRepository approvalRepository;

    @Autowired
    public RoadmapJobVacancyService(RoadmapJobVacancyRepository roadmapJobVacancyRepository,
            JobVacancyRepository jobVacancyRepository,
            CandidateEmployeeRepository candidateEmployeRepository,
            ApprovalRepository approvalRepository) {
        this.roadmapJobVacancyRepository = roadmapJobVacancyRepository;
        this.jobVacancyRepository = jobVacancyRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
        this.approvalRepository = approvalRepository;
    }

    public List<JobVacancyDto> getAllApply(String Username) {
        return roadmapJobVacancyRepository.getApplyByUser(Username);
    }

    public List<RoadmapJobVacancyDto> getApplyDetailById(String username, Integer job_id) {
        return roadmapJobVacancyRepository.getApplyDetailForUser(username, job_id);
    }

    public List<CandidateEmployeeDto> getAllCandidateByIdJob(Integer job_id) {
        return roadmapJobVacancyRepository.getCandidateApplyById(job_id);
    }

    public boolean save(RoadmapJobVacancyDto roadmapJobVacancyDto) {
        try {
            RoadmapJobVacancy roadmapJobVacancy = new RoadmapJobVacancy();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatted = now.format(formatter);
            roadmapJobVacancy.setAction(roadmapJobVacancyDto.getAction());
            roadmapJobVacancy.setFeedback(roadmapJobVacancyDto.getFeedback());
            roadmapJobVacancy.setSubmit_date(formatted);
            roadmapJobVacancy.setCandidateEmployee(
                    candidateEmployeRepository.findById(roadmapJobVacancyDto.getCandidateEmployee()).orElse(null));
            roadmapJobVacancy
                    .setJobVacancy(jobVacancyRepository.findById(roadmapJobVacancyDto.getJobVacancy()).orElse(null));
            

            roadmapJobVacancyRepository.save(roadmapJobVacancy);

            Approval approval = new Approval(
                null,
                "Need Approval",
                "Waiting for Approval",
                null,
                formatted,
                roadmapJobVacancy,
                null
            );
            approvalRepository.save(approval);
            

            return roadmapJobVacancyRepository.findById(roadmapJobVacancy.getId()).isPresent();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
