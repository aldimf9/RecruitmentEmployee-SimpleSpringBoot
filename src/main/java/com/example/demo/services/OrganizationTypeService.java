package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.OrganizationType;
import com.example.demo.models.dto.OrganizationTypeDto;
import com.example.demo.repositories.OrganizationTypeRepository;


@Service
public class OrganizationTypeService {
    private final OrganizationTypeRepository organizationTypeRepository;

    @Autowired
    public OrganizationTypeService(OrganizationTypeRepository organizationTypeRepository){
        this.organizationTypeRepository = organizationTypeRepository;
    }

    public List<OrganizationType> getAll(){
        return organizationTypeRepository.findAll();
    }

    public OrganizationType getById(Integer id){
        return organizationTypeRepository.findById(id).orElse(null);
    }

    public boolean save(OrganizationTypeDto organizationTypesDto){
        try {
            OrganizationType organizationTypes = new OrganizationType();
            organizationTypes.setId(organizationTypesDto.getId());
            organizationTypes.setName(organizationTypesDto.getName());

            organizationTypeRepository.save(organizationTypes);

            return organizationTypeRepository.findById(organizationTypesDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        organizationTypeRepository.deleteById(id);
        return !organizationTypeRepository.findById(id).isPresent();
    }
}
