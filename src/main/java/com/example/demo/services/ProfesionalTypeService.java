package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProfesionalType;
import com.example.demo.models.dto.ProfesionalTypeDto;
import com.example.demo.repositories.ProfesionalTypeRepository;

@Service
public class ProfesionalTypeService {
    private final ProfesionalTypeRepository profesionalTypesRepository;

    @Autowired
    public ProfesionalTypeService(ProfesionalTypeRepository profesionalTypesRepository){
        this.profesionalTypesRepository = profesionalTypesRepository;
    }

    public List<ProfesionalType> getAll(){
        return profesionalTypesRepository.findAll();
    }

    public ProfesionalType getById(Integer id){
        return profesionalTypesRepository.findById(id).orElse(null);
    }

    public boolean save(ProfesionalTypeDto profesionalTypesDto){
        try {
            ProfesionalType profesionalTypes = new ProfesionalType();
            profesionalTypes.setId(profesionalTypesDto.getId());
            profesionalTypes.setName(profesionalTypesDto.getName());

            profesionalTypesRepository.save(profesionalTypes);

            return profesionalTypesRepository.findById(profesionalTypesDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        profesionalTypesRepository.deleteById(id);
        return !profesionalTypesRepository.findById(id).isPresent();
    }
}
