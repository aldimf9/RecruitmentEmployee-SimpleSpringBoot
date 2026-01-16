package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.models.InterviewSchedule;
import com.example.demo.models.dto.InterviewScheduleDto;
import com.example.demo.models.enums.InterviewStatus;
import com.example.demo.repositories.InterviewScheduleRepository;
import com.example.demo.repositories.RoadmapJobVacancyRepository;
import com.example.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewScheduleService {
    private final InterviewScheduleRepository interviewScheduleRepository;

    private final RoadmapJobVacancyRepository roadmapJobVacancyRepository;

    private final UserRepository userRepository;

    public List<InterviewScheduleDto> getAllData() {
        return interviewScheduleRepository.getAllData();
    }

    public List<InterviewScheduleDto> getDetailByRoadmap(Integer roadmap) {
        return interviewScheduleRepository.getDetailByJobRoadmap(roadmap);
    }

    public List<InterviewScheduleDto> getDetailByInterviewer(Integer interviewer) {
        return interviewScheduleRepository.getAllDataByInterviewer(interviewer);
    }

    public InterviewScheduleDto getDetailById(Integer id) {
        return interviewScheduleRepository.getAllDataById(id);
    }

    public String updateStatus(InterviewScheduleDto interviewScheduleDto) {
        try {

            InterviewSchedule interviewSchedule = interviewScheduleRepository.getById(interviewScheduleDto.getId());

            interviewSchedule.setInterviewStatus(interviewScheduleDto.getInterviewStatus());

            interviewScheduleRepository.save(interviewSchedule);

            return "Has Been Updated";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update Failed";
        }
    }

    @Transactional
    public String InsertSchedule(InterviewScheduleDto interviewScheduleDto) {
        try {
            InterviewSchedule interviewSchedule = new InterviewSchedule();

            interviewSchedule.setRoadmap(roadmapJobVacancyRepository.getById(interviewScheduleDto.getRdmp()));

            interviewSchedule.setInterviewer(userRepository.getById(interviewScheduleDto.getInterviewer()));

            interviewSchedule.setInterviewType(interviewSchedule.getInterviewType());

            interviewSchedule.setInterviewDate(interviewScheduleDto.getInterviewDate());

            interviewSchedule.setLocation(interviewScheduleDto.getLocation());

            interviewSchedule.setInterviewStatus(InterviewStatus.SCHEDULED);

            interviewScheduleRepository.save(interviewSchedule);

            return "Has Been Updated";
        } catch (Exception e) {
            e.printStackTrace();
            return "Update Failed";
        }

    }
}
