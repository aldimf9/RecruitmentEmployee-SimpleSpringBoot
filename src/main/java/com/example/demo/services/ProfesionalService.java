package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Profesional;
import com.example.demo.models.dto.ProfesionalDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.ProfesionalRepository;
import com.example.demo.repositories.ProfesionalTypeRepository;

@Service
public class ProfesionalService {
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalTypeRepository profesionalTypesRepository;
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public ProfesionalService(ProfesionalRepository profesionalRepository,
    ProfesionalTypeRepository profesionalTypesRepository,
    CandidateEmployeeRepository candidateEmployeRepository){
        this.profesionalRepository = profesionalRepository;
        this.profesionalTypesRepository = profesionalTypesRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<Profesional> getAll(){
        return profesionalRepository.findAll();
    }

    public Profesional getById(Integer id){
        return profesionalRepository.findById(id).orElse(null);
    }

    public boolean save(ProfesionalDto profesionalDto){
        try {
            Profesional profesional = new Profesional();
            profesional.setId(profesionalDto.getId());
            profesional.setName(profesionalDto.getName());
            profesional.setDescription(profesionalDto.getDescription());
            profesional.setAdditionaly_file(profesionalDto.getAdditionaly_file());
            profesional.setStart_date(profesionalDto.getStart_date());
            profesional.setFinish_date(profesionalDto.getFinish_date());
            profesional.setLocation(profesionalDto.getLocation());
            profesional.setProfesionalTypes(profesionalTypesRepository.findById(profesionalDto.getProfesionalType()).orElse(null));
            profesional.setCandidateEmployee(candidateEmployeRepository.findById(profesionalDto.getCandidateEmployee()).orElse(null));

            profesionalRepository.save(profesional);

            return profesionalRepository.findById(profesionalDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        profesionalRepository.deleteById(id);
        return !profesionalRepository.findById(id).isPresent();
    }
}
