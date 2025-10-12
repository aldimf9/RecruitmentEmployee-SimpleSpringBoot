package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Project;
import com.example.demo.models.dto.ProjectDto;
import com.example.demo.repositories.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public Project getById(Integer id){
        return projectRepository.findById(id).orElse(null);
    }

    public boolean save(ProjectDto projectDto){
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setAdditionaly_file(projectDto.getAdditionaly_file());
        project.setOrganizationTypes(projectDto.getOrganizationTypes());
        project.setCandidateEmployee(projectDto.getCandidateEmployee());

        projectRepository.save(project);

        return projectRepository.findById(projectDto.getId()).isPresent();
    }

    public boolean remove(Integer id){
        projectRepository.deleteById(id);
        return !projectRepository.findById(id).isPresent();
    }
}
