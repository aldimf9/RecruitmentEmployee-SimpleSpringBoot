package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Profesional;
import com.example.demo.models.dto.ProfesionalDto;
import com.example.demo.repositories.ProfesionalRepository;

@Service
public class ProfesionalService {
    private final ProfesionalRepository profesionalRepository;

    @Autowired
    public ProfesionalService(ProfesionalRepository profesionalRepository){
        this.profesionalRepository = profesionalRepository;
    }

    public List<Profesional> getAll(){
        return profesionalRepository.findAll();
    }

    public Profesional getById(Integer id){
        return profesionalRepository.findById(id).orElse(null);
    }

    public boolean save(ProfesionalDto profesionalDto){
        Profesional profesional = new Profesional();
        profesional.setId(profesionalDto.getId());
        profesional.setName(profesionalDto.getName());
        profesional.setDescription(profesionalDto.getDescription());
        profesional.setAdditionaly_file(profesionalDto.getAdditionaly_file());
        profesional.setStart_date(profesionalDto.getStart_date());
        profesional.setFinish_date(profesionalDto.getFinish_date());
        profesional.setLocation(profesionalDto.getLocation());
        profesional.setOrganizationTypes(profesionalDto.getOrganizationTypes());
        profesional.setCandidateEmployee(profesionalDto.getCandidateEmployee());

        profesionalRepository.save(profesional);

        return profesionalRepository.findById(profesionalDto.getId()).isPresent();
    }

    public boolean remove(Integer id){
        profesionalRepository.deleteById(id);
        return !profesionalRepository.findById(id).isPresent();
    }
}
