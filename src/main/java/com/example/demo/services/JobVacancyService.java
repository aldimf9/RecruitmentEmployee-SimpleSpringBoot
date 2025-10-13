package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.JobVacancy;
import com.example.demo.models.dto.JobVacancyDto;
import com.example.demo.repositories.JobVacancyRepository;

public class JobVacancyService {
    private final JobVacancyRepository jobVacancyRepository;

    @Autowired
    public JobVacancyService(JobVacancyRepository jobVacancyRepository){
        this.jobVacancyRepository = jobVacancyRepository;
    }

    public List<JobVacancy> getAll(){
        return jobVacancyRepository.findAll();
    }

    public JobVacancy getById(Integer id){
        return jobVacancyRepository.findById(id).orElse(null);
    }

    public boolean save(JobVacancyDto jobVacancyDto){
        try {
            JobVacancy jobVacancy = new JobVacancy();
            jobVacancy.setId(jobVacancyDto.getId());
            jobVacancy.setName(jobVacancyDto.getName());
            jobVacancy.setDescription(jobVacancyDto.getDescription());
            jobVacancy.setStatus(jobVacancyDto.getStatus());

            jobVacancyRepository.save(jobVacancy);

            return jobVacancyRepository.findById(jobVacancy.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        jobVacancyRepository.deleteById(id);
        return !jobVacancyRepository.findById(id).isPresent();
    }
}
