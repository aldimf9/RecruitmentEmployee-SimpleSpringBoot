package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProjectTypes;
import com.example.demo.models.dto.ProjectTypesDto;
import com.example.demo.repositories.ProjectTypesRepository;

@Service
public class ProjectTypesService {
    private final ProjectTypesRepository projectTypesRepository;

    @Autowired
    public ProjectTypesService(ProjectTypesRepository projectTypesRepository){
        this.projectTypesRepository = projectTypesRepository;
    }

    public List<ProjectTypes> getAll(){
        return projectTypesRepository.findAll();
    }

    public ProjectTypes getById(Integer id){
        return projectTypesRepository.findById(id).orElse(null);
    }

    public boolean save(ProjectTypesDto projectTypesDto){
        try {
            ProjectTypes projectTypes = new ProjectTypes();
            projectTypes.setId(projectTypesDto.getId());
            projectTypes.setName(projectTypesDto.getName());

            projectTypesRepository.save(projectTypes);

            return projectTypesRepository.findById(projectTypesDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        projectTypesRepository.deleteById(id);
        return !projectTypesRepository.findById(id).isPresent();
    }
}
