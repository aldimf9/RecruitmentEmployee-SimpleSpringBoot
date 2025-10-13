package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.dto.RoadmapJobVacancyDto;
import com.example.demo.repositories.CandidateEmployeRepository;
import com.example.demo.repositories.JobVacancyRepository;
import com.example.demo.repositories.RoadmapJobVacancyRepository;

@Service
public class RoadmapJobVacancyService {
    private final RoadmapJobVacancyRepository roadmapJobVacancyRepository;
    private final JobVacancyRepository jobVacancyRepository;
    private final CandidateEmployeRepository candidateEmployeRepository;

    public RoadmapJobVacancyService(RoadmapJobVacancyRepository roadmapJobVacancyRepository,
    JobVacancyRepository jobVacancyRepository,
    CandidateEmployeRepository candidateEmployeRepository){
        this.roadmapJobVacancyRepository = roadmapJobVacancyRepository;
        this.jobVacancyRepository = jobVacancyRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<RoadmapJobVacancy> getAll(){
        return roadmapJobVacancyRepository.findAll();
    }

    public RoadmapJobVacancy getById(Integer id){
        return roadmapJobVacancyRepository.findById(id).orElse(null);
    }

    public boolean save(RoadmapJobVacancyDto roadmapJobVacancyDto){
        try {
            RoadmapJobVacancy roadmapJobVacancy = new RoadmapJobVacancy();
            roadmapJobVacancy.setId(roadmapJobVacancyDto.getId());
            roadmapJobVacancy.setAction(roadmapJobVacancyDto.getAction());
            roadmapJobVacancy.setFeedback(roadmapJobVacancyDto.getFeedback());
            roadmapJobVacancy.setSubmit_date(roadmapJobVacancyDto.getSubmit_date());
            roadmapJobVacancy.setJobVacancy(jobVacancyRepository.findById(roadmapJobVacancyDto.getJobVacancy()).orElse(null));
            roadmapJobVacancy.setCandidateEmployee(candidateEmployeRepository.findById(roadmapJobVacancyDto.getCandidateEmployee()).orElse(null));

            roadmapJobVacancyRepository.save(roadmapJobVacancy);

            return roadmapJobVacancyRepository.findById(roadmapJobVacancyDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        roadmapJobVacancyRepository.deleteById(id);
        return !roadmapJobVacancyRepository.findById(id).isPresent();
    }
}
