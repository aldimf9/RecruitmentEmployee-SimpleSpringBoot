package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProfesionalTypes;
import com.example.demo.models.dto.ProfesionalTypesDto;
import com.example.demo.repositories.ProfesionalTypesRepository;

@Service
public class ProfesionalTypesService {
    private final ProfesionalTypesRepository profesionalTypesRepository;

    @Autowired
    public ProfesionalTypesService(ProfesionalTypesRepository profesionalTypesRepository){
        this.profesionalTypesRepository = profesionalTypesRepository;
    }

    public List<ProfesionalTypes> getAll(){
        return profesionalTypesRepository.findAll();
    }

    public ProfesionalTypes getById(Integer id){
        return profesionalTypesRepository.findById(id).orElse(null);
    }

    public boolean save(ProfesionalTypesDto profesionalTypesDto){
        ProfesionalTypes profesionalTypes = new ProfesionalTypes();
        profesionalTypes.setId(profesionalTypesDto.getId());
        profesionalTypes.setName(profesionalTypesDto.getName());

        profesionalTypesRepository.save(profesionalTypes);

        return profesionalTypesRepository.findById(profesionalTypesDto.getId()).isPresent();
    }

    public boolean remove(Integer id){
        profesionalTypesRepository.deleteById(id);
        return !profesionalTypesRepository.findById(id).isPresent();
    }
}
