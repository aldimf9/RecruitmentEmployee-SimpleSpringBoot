package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.RoadmapJobVacancy;
import com.example.demo.models.dto.RoadmapJobVacancyDto;
import com.example.demo.repositories.RoadmapJobVacancyRepository;

@Service
public class RoadmapJobVacancyService {
    private final RoadmapJobVacancyRepository roadmapJobVacancyRepository;

    public RoadmapJobVacancyService(RoadmapJobVacancyRepository roadmapJobVacancyRepository){
        this.roadmapJobVacancyRepository = roadmapJobVacancyRepository;
    }

    public List<RoadmapJobVacancy> getAll(){
        return roadmapJobVacancyRepository.findAll();
    }

    public RoadmapJobVacancy getById(Integer id){
        return roadmapJobVacancyRepository.findById(id).orElse(null);
    }

    public boolean save(RoadmapJobVacancyDto roadmapJobVacancyDto){
        RoadmapJobVacancy roadmapJobVacancy = new RoadmapJobVacancy();
        roadmapJobVacancy.setId(roadmapJobVacancyDto.getId());
        roadmapJobVacancy.setAction(roadmapJobVacancyDto.getAction());
        roadmapJobVacancy.setFeedback(roadmapJobVacancyDto.getFeedback());
        roadmapJobVacancy.setSubmit_date(roadmapJobVacancyDto.getSubmit_date());
        roadmapJobVacancy.setJobVacancy(roadmapJobVacancyDto.getJobVacancy());
        roadmapJobVacancy.setCandidateEmployee(roadmapJobVacancyDto.getCandidateEmployee());

        roadmapJobVacancyRepository.save(roadmapJobVacancy);

        return roadmapJobVacancyRepository.findById(roadmapJobVacancyDto.getId()).isPresent();
    }

    public boolean remove(Integer id){
        roadmapJobVacancyRepository.deleteById(id);
        return !roadmapJobVacancyRepository.findById(id).isPresent();
    }
}
