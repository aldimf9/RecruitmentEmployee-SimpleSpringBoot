package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Certification;
import com.example.demo.models.dto.CertificationDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.CertificationRepository;
import com.example.demo.repositories.CertificationTypeRepository;

@Service
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final CertificationTypeRepository certificationTypesRepository;
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public CertificationService(CertificationRepository certificationRepository,
            CertificationTypeRepository certificationTypesRepository,
            CandidateEmployeeRepository candidateEmployeRepository) {
        this.certificationRepository = certificationRepository;
        this.certificationTypesRepository = certificationTypesRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public CertificationDto getById(Integer id) {
        return certificationRepository.getCertificationDataById(id);
    }

    public List<CertificationDto> getCertificationDataByUserId(Integer id) {
        return certificationRepository.getAllCertificationDataByCandidateId(id);
    }

    public boolean save(CertificationDto certificationDto) {
        try {
            Certification certification = new Certification();

            // Update
            if (certificationDto.getId() != 0) {
                certification = certificationRepository.getById(certificationDto.getId());
            } else {
                certification.setCandidateEmployee(
                        candidateEmployeRepository.findById(certificationDto.getCandidateEmployee()).orElse(null));
            }
            certification.setName(certificationDto.getName());
            certification.setDescription(certificationDto.getDescription());
            certification.setAdditionaly_file(certificationDto.getAddtional_file());
            certification.setAvailable_start_date(certificationDto.getAvailable_start_date());
            certification.setAvailable_end_date(certificationDto.getAvailable_end_date());
            certification.setCertificationTypes(
                    certificationTypesRepository.findById(certificationDto.getCertificationTypeId()).orElse(null));

            certificationRepository.save(certification);

            return certificationRepository.findById(certificationDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id) {
        certificationRepository.deleteById(id);
        return !certificationRepository.findById(id).isPresent();
    }
}
