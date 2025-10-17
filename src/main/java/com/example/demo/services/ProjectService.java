package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Project;
import com.example.demo.models.dto.ProjectDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectTypeRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectTypeRepository projectTypesRepository; 
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
    ProjectTypeRepository projectTypesRepository,
   CandidateEmployeeRepository candidateEmployeRepository){
        this.projectRepository = projectRepository;
        this.projectTypesRepository = projectTypesRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public Project getById(Integer id){
        return projectRepository.findById(id).orElse(null);
    }

    public boolean save(ProjectDto projectDto){
        try {
            Project project = new Project();
            project.setId(projectDto.getId());
            project.setName(projectDto.getName());
            project.setDescription(projectDto.getDescription());
            project.setAdditionaly_file(projectDto.getAdditionaly_file());
            project.setProjectTypes(projectTypesRepository.findById(projectDto.getProjectType()).orElse(null));
            project.setCandidateEmployee(candidateEmployeRepository.findById(projectDto.getCandidateEmployee()).orElse(null));

            projectRepository.save(project);

            return projectRepository.findById(projectDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
        
    }

    public boolean remove(Integer id){
        projectRepository.deleteById(id);
        return !projectRepository.findById(id).isPresent();
    }
}
