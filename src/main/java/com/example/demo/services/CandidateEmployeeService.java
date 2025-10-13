package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.CertificationDto;
import com.example.demo.repositories.CandidateEmployeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class CandidateEmployeeService {
    private final CandidateEmployeRepository candidateEmployeRepository;
    private final UserRepository userRepository;

    @Autowired
    public CandidateEmployeeService(CandidateEmployeRepository candidateEmployeRepository, UserRepository userRepository){
        this.candidateEmployeRepository = candidateEmployeRepository;
        this.userRepository = userRepository;
    }

    public List<CandidateEmployee> getAll(){
        return candidateEmployeRepository.findAll();
    }

    public CandidateEmployee getById(Integer id){
        return candidateEmployeRepository.findById(id).orElse(null);
    }

    public boolean save(CandidateEmployeeDto candidateEmployeeDto){
        try {
            CandidateEmployee candidateEmployee = new CandidateEmployee();
            candidateEmployee.setId(candidateEmployeeDto.getId());
            candidateEmployee.setFirstName(candidateEmployeeDto.getFirstName());
            candidateEmployee.setLastName(candidateEmployeeDto.getLastName());
            candidateEmployee.setAddress(candidateEmployeeDto.getAddress());
            candidateEmployee.setPhoneNumber(candidateEmployeeDto.getPhoneNumber());
            candidateEmployee.setBirth_date(candidateEmployeeDto.getBirth_date());
            candidateEmployee.setCity_date(candidateEmployeeDto.getCity_date());
            candidateEmployee.setCuriculumVitae(candidateEmployeeDto.getCuriculumVitae());
            candidateEmployee.setPortofolio(candidateEmployeeDto.getPortofolio());
            candidateEmployee.setUser(userRepository.findById(candidateEmployeeDto.getUser()).orElse(null));

            candidateEmployeRepository.save(candidateEmployee);

            return candidateEmployeRepository.findById(candidateEmployeeDto.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        candidateEmployeRepository.deleteById(id);
        return !candidateEmployeRepository.findById(id).isPresent();
    }
}
