package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProjectType;
import com.example.demo.models.dto.ProjectTypeDto;
import com.example.demo.repositories.ProjectTypeRepository;

@Service
public class ProjectTypeService {
    private final ProjectTypeRepository projectTypesRepository;

    @Autowired
    public ProjectTypeService(ProjectTypeRepository projectTypesRepository){
        this.projectTypesRepository = projectTypesRepository;
    }

    public List<ProjectType> getAll(){
        return projectTypesRepository.findAll();
    }

    public ProjectType getById(Integer id){
        return projectTypesRepository.findById(id).orElse(null);
    }

    public boolean save(ProjectTypeDto projectTypesDto){
        try {
            ProjectType projectTypes = new ProjectType();
            if (projectTypesDto.getId() != null) {
                projectTypes.setId(projectTypesDto.getId());
            }
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
