package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Organization;
import com.example.demo.models.dto.OrganizationDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.OrganizationRepository;
import com.example.demo.repositories.OrganizationTypeRepository;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationTypeRepository organizationTypeRepository;
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository,
    OrganizationTypeRepository organizationTypeRepository,
    final CandidateEmployeeRepository candidateEmployeRepository){
        this.organizationRepository = organizationRepository;
        this.organizationTypeRepository = organizationTypeRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<Organization> getAll(){
        return organizationRepository.findAll();
    }

    public Organization getById(Integer id){
        return organizationRepository.findById(id).orElse(null);
    }

    public boolean save(OrganizationDto organizationDto){
        try {
            Organization organization = new Organization();
            organization.setId(organizationDto.getId());
            organization.setName(organizationDto.getName());
            organization.setDescription(organizationDto.getDescription());
            organization.setAdditionaly_file(organizationDto.getAdditionaly_file());
            organization.setStart_date(organizationDto.getStart_date());
            organization.setFinish_date(organizationDto.getFinish_date());
            organization.setLocation(organizationDto.getLocation());
            organization.setOrganizationTypes(organizationTypeRepository.findById(organizationDto.getOrganizationType()).orElse(null));
            organization.setCandidateEmployee(candidateEmployeRepository.findById(organizationDto.getCandidateEmployee()).orElse(null));

            organizationRepository.save(organization);

            return organizationRepository.findById(organizationDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        organizationRepository.deleteById(id);
        return !organizationRepository.findById(id).isPresent();
    }
}
