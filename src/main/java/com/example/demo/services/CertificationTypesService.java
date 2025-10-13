package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CertificationTypes;
import com.example.demo.models.dto.CertificationTypesDto;
import com.example.demo.repositories.CertificationTypesRepository;

@Service
public class CertificationTypesService {
    private final CertificationTypesRepository certificationTypesRepository;

    @Autowired
    public CertificationTypesService(CertificationTypesRepository certificationTypesRepository){
        this.certificationTypesRepository = certificationTypesRepository;
    }

    public List<CertificationTypes> getAll(){
        return certificationTypesRepository.findAll();
    }

    public CertificationTypes getById(Integer id){
        return certificationTypesRepository.findById(id).orElse(null);
    }

    public boolean save(CertificationTypesDto certificationTypesDto){
        try {
            CertificationTypes certificationTypes = new CertificationTypes();
            certificationTypes.setId(certificationTypesDto.getId());
            certificationTypes.setName(certificationTypesDto.getName());

            certificationTypesRepository.save(certificationTypes);

            return certificationTypesRepository.findById(certificationTypesDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }   
    }

    public boolean remove(Integer id){
        certificationTypesRepository.deleteById(id);
        return !certificationTypesRepository.findById(id).isPresent();
    }
}
