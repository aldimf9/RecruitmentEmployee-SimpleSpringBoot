package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Certification;
import com.example.demo.models.dto.CertificationDto;
import com.example.demo.repositories.CertificationRepository;

@Service
public class CertificationService {
    private final CertificationRepository certificationRepository;

    @Autowired
    public CertificationService(CertificationRepository certificationRepository){
        this.certificationRepository = certificationRepository;
    }

    public List<Certification> getAll(){
        return certificationRepository.findAll();
    }

    public Certification getById(Integer id){
        return certificationRepository.findById(id).orElse(null);
    }

    public boolean save(CertificationDto certificationDto){
        Certification certification = new Certification();
        certification.setId(certificationDto.getId());
        certification.setName(certificationDto.getName());
        certification.setDescription(certificationDto.getDescription());
        certification.setAdditionaly_file(certificationDto.getAddtional_file());
        certification.setAvailable_start_date(certificationDto.getAvailable_start_date());
        certification.setAvailable_end_date(certificationDto.getAvailable_end_date());
        certification.setCandidateEmployee(certificationDto.getCandidateEmployee());
        certification.setCertificationTypes(certificationDto.getCertificationTypes());

        certificationRepository.save(certification);

        return certificationRepository.findById(certificationDto.getId()).isPresent();
    }

    public boolean remove(Integer id){
        certificationRepository.deleteById(id);
        return !certificationRepository.findById(id).isPresent();
    }
}
