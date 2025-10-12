package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OrganizationTypes;
import com.example.demo.models.dto.OrganizationTypesDto;
import com.example.demo.repositories.OrganizationTypeRepository;


@Service
public class OrganizationTypesService {
    private final OrganizationTypeRepository organizationTypeRepository;

    @Autowired
    public OrganizationTypesService(OrganizationTypeRepository organizationTypeRepository){
        this.organizationTypeRepository = organizationTypeRepository;
    }

    public List<OrganizationTypes> getAll(){
        return organizationTypeRepository.findAll();
    }

    public OrganizationTypes getById(Integer id){
        return organizationTypeRepository.findById(id).orElse(null);
    }

    public boolean save(OrganizationTypesDto organizationTypesDto){
        OrganizationTypes organizationTypes = new OrganizationTypes();
        organizationTypes.setId(organizationTypesDto.getId());
        organizationTypes.setName(organizationTypesDto.getName());

        organizationTypeRepository.save(organizationTypes);

        return organizationTypeRepository.findById(organizationTypesDto.getId()).isPresent();
    }

    public boolean remove(Integer id){
        organizationTypeRepository.deleteById(id);
        return !organizationTypeRepository.findById(id).isPresent();
    }
}
