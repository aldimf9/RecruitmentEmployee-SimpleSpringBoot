package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Approval;
import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.User;
import com.example.demo.models.dto.ApprovalDto;
import com.example.demo.models.enums.ApprovalStatus;
import com.example.demo.models.enums.PhaseRecruitment;
import com.example.demo.repositories.ApprovalRepository;
import com.example.demo.repositories.RoadmapJobVacancyRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.DateFormater;

@Service
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final UserRepository userRepository;
    private final RoadmapJobVacancyRepository roadmapJobVacancyRepository;

    @Autowired
    public ApprovalService(ApprovalRepository approvalRepository, UserRepository userRepository,
            RoadmapJobVacancyRepository roadmapJobVacancyRepository) {
        this.approvalRepository = approvalRepository;
        this.userRepository = userRepository;
        this.roadmapJobVacancyRepository = roadmapJobVacancyRepository;
    }

    public List<ApprovalDto> getAllDataByAction(String action) {
        return approvalRepository.getAllData(PhaseRecruitment.valueOf(action));
    }

    @Transactional
    public boolean save(ApprovalDto approvalDto) {
        try {
            DateFormater dateFormat = new DateFormater();

            // Get data roadmap
            RoadmapJobVacancy getRoadmapJobVacancy = roadmapJobVacancyRepository.getById(approvalDto.getRdmpId());

            User getUser = userRepository.getById(approvalDto.getUserId());

            // Get data approval
            Approval getApproval = approvalRepository.getById(approvalDto.getId());

            // Adjust note to feedback roadmap
            getRoadmapJobVacancy.setFeedback(getRoadmapJobVacancy.getFeedback() + approvalDto.getNote());

            // Update Approval
            getApproval.setStatus(approvalDto.getStatus());
            getApproval.setApprovalDate(dateFormat.formater());
            getApproval.setUser(getUser);
            approvalRepository.save(getApproval);

            if (approvalDto.getStatus().equals(ApprovalStatus.HAS_BEEN_REJECTED)) {
                // update roadmap
                roadmapJobVacancyRepository.save(getRoadmapJobVacancy);

                return roadmapJobVacancyRepository.existsById(getApproval.getId());
            }

            // Get next action for roadmap
            PhaseRecruitment nextStep = getRoadmapJobVacancy.getAction();
            switch (nextStep) {
                case APPLY:
                    nextStep = PhaseRecruitment.INTERVIEW_HR;
                    break;
                case INTERVIEW_HR:
                    nextStep = PhaseRecruitment.INTERVIEW_USER;
                    break;
                case INTERVIEW_USER:
                    nextStep = PhaseRecruitment.PRE_OFFERING;
                    break;
                case PRE_OFFERING:
                    nextStep = PhaseRecruitment.ON_BOARDING;
                    break;
                default:
                    break;
            }

            // Update Roadmap Job Vacancy
            getRoadmapJobVacancy.setAction(nextStep);
            roadmapJobVacancyRepository.save(getRoadmapJobVacancy);

            if (!nextStep.equals(PhaseRecruitment.ON_BOARDING)) {
                // Create new Approval
                Approval approval = new Approval(
                        0,
                        ApprovalStatus.WAITING_FOR_APPROVAL,
                        null,
                        dateFormat.formater(),
                        nextStep,
                        getRoadmapJobVacancy,
                        getUser);

                approvalRepository.save(approval);
            }

            return roadmapJobVacancyRepository.existsById(getRoadmapJobVacancy.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
