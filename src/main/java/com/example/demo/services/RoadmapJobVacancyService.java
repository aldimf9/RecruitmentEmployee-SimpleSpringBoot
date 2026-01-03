package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Approval;
import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.JobVacancyDto;
import com.example.demo.models.dto.RoadmapJobVacancyDto;
import com.example.demo.models.enums.ApprovalStatus;
import com.example.demo.models.enums.PhaseRecruitment;
import com.example.demo.repositories.ApprovalRepository;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.JobVacancyRepository;
import com.example.demo.repositories.RoadmapJobVacancyRepository;
import com.example.demo.utils.DateFormater;

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

    public List<RoadmapJobVacancyDto> getAllDataByApply() {
        return roadmapJobVacancyRepository.getAllDataNewApply();
    }

    @Transactional
    public boolean save(RoadmapJobVacancyDto roadmapJobVacancyDto) {
        try {

            RoadmapJobVacancy roadmapJobVacancy = new RoadmapJobVacancy();

            DateFormater dateFormat = new DateFormater();

            roadmapJobVacancy.setAction(roadmapJobVacancyDto.getAction());
            roadmapJobVacancy.setFeedback(roadmapJobVacancyDto.getFeedback());
            roadmapJobVacancy.setSubmit_date(dateFormat.formater());
            roadmapJobVacancy.setCandidateEmployee(
                    candidateEmployeRepository.findById(roadmapJobVacancyDto.getCandidateEmployee()).orElse(null));
            roadmapJobVacancy
                    .setJobVacancy(jobVacancyRepository.findById(roadmapJobVacancyDto.getJobVacancy()).orElse(null));
            roadmapJobVacancy.setInterviewSchedule(null);

            List<Approval> approvals = new ArrayList<>();
            Approval approval = new Approval(
                    ApprovalStatus.WAITING_FOR_APPROVAL,
                    null,
                    dateFormat.formater(),
                    PhaseRecruitment.APPLY,
                    roadmapJobVacancy,
                    null);
            approvals.add(approval);
            roadmapJobVacancy.setApproval(approvals);

            roadmapJobVacancyRepository.save(roadmapJobVacancy);

            return roadmapJobVacancyRepository.findById(roadmapJobVacancy.getId()).isPresent();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
