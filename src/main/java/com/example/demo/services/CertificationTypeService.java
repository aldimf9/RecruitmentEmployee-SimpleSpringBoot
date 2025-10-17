package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CertificationType;
import com.example.demo.models.dto.CertificationTypeDto;
import com.example.demo.repositories.CertificationTypeRepository;

@Service
public class CertificationTypeService {
    private final CertificationTypeRepository certificationTypesRepository;

    @Autowired
    public CertificationTypeService(CertificationTypeRepository certificationTypesRepository){
        this.certificationTypesRepository = certificationTypesRepository;
    }

    public List<CertificationType> getAll(){
        return certificationTypesRepository.findAll();
    }

    public CertificationType getById(Integer id){
        return certificationTypesRepository.findById(id).orElse(null);
    }

    public boolean save(CertificationTypeDto certificationTypesDto){
        try {
            CertificationType certificationTypes = new CertificationType();
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
