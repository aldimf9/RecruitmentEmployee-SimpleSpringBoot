package com.example.demo.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Approval;
import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.dto.ApprovalDto;
import com.example.demo.repositories.ApprovalRepository;
import com.example.demo.repositories.RoadmapJobVacancyRepository;
import com.example.demo.repositories.UserRepository;

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

    public List<ApprovalDto> getAllData(String action) {
        return approvalRepository.getAllData(action);
    }

    public boolean save(ApprovalDto approvalDto) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatted = now.format(formatter);

            // Get data roadmap
            RoadmapJobVacancy getRoadmapJobVacancy = roadmapJobVacancyRepository.getById(approvalDto.getId());

            // Get data approval
            Approval getApproval = approvalRepository.getById(approvalDto.getId());

            if (approvalDto.getStatus().equals("rejected")) {
                getRoadmapJobVacancy.setFeedback(approvalDto.getNote());
                // update roadmap
                roadmapJobVacancyRepository.save(getRoadmapJobVacancy);

                getApproval.setStatus("Done Approval");
                getApproval.setApprovalDate(formatted);
                getApproval.setNote(approvalDto.getStatus());
                // update approval
                approvalRepository.save(getApproval);
            }

            // Get next action for roadmap
            String nextStep = getRoadmapJobVacancy.getAction();
            switch (nextStep) {
                case "Screening CV":
                    nextStep = "Interview HR";
                    break;
                case "Interview HR":
                    nextStep = "Interview User";
                    break;
                case "Interview User":
                    nextStep = "Offering";
                    break;
                case "Offering":
                    nextStep = "On Boarding";
                    break;
                default:
                    break;
            }

            // Create new roadmap
            RoadmapJobVacancy roadmapJobVacancy = new RoadmapJobVacancy(
                    0,
                    nextStep,
                    approvalDto.getNote(),
                    formatted,
                    getRoadmapJobVacancy.getCandidateEmployee(),
                    getRoadmapJobVacancy.getJobVacancy());

            roadmapJobVacancyRepository.save(roadmapJobVacancy);

            if (!nextStep.equals("On Boarding")) {
                // Create new Approval
                Approval approval = new Approval(
                        0,
                        "Need Approval",
                        null,
                        null,
                        formatted,
                        roadmapJobVacancy,
                        userRepository.getById(approvalDto.getUserId()));

                approvalRepository.save(approval);

                // update roadmap.approval with new approval
                roadmapJobVacancy.setApproval(approval);

                roadmapJobVacancyRepository.save(roadmapJobVacancy);

                return approvalRepository.existsById(approval.getId());
            }

            return roadmapJobVacancyRepository.existsById(roadmapJobVacancy.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
