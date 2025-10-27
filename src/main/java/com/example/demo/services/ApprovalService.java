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

    public List<ApprovalDto> getAllData(){
        return approvalRepository.getAllData();
    }

    public boolean save(ApprovalDto approvalDto) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatted = now.format(formatter);

            Approval approval = approvalRepository.getById(approvalDto.getId());
            String feedback = "";
            if (approvalDto.getNote().equals("Approved")) {
                feedback = "Best regards and please respond to our email";
            } else if (approvalDto.getNote().equals("Rejected")) {
                feedback = "Thank you and keep fighting";
            } else {
                feedback = "Waiting for Approval";
            }

            approval.setApprovalDate(formatted);
            approval.setStatus(approvalDto.getStatus());
            approval.setNote(approvalDto.getNote());
            approval.setUser(userRepository.getById(approvalDto.getUserId()));
            approval.setRoadmapJobVacancy(roadmapJobVacancyRepository.getById(approvalDto.getId()));

            approvalRepository.save(approval);

            RoadmapJobVacancy roadmapJobVacancy = roadmapJobVacancyRepository.getById(approvalDto.getId());
            roadmapJobVacancy.setFeedback(feedback);

            roadmapJobVacancyRepository.save(roadmapJobVacancy);

           

            return approvalRepository.existsById(approval.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
