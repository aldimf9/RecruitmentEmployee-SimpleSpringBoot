package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.RoadmapJobVacancyDto;
import com.example.demo.repositories.CandidateEmployeeRepository;

@Service
public class CandidateEmployeeService {
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public CandidateEmployeeService(CandidateEmployeeRepository candidateEmployeRepository){
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<CandidateEmployeeDto> getAll(){
        return candidateEmployeRepository.getAllData();
    }

    public CandidateEmployeeDto getById(Integer id){
        return candidateEmployeRepository.getDataById(id);
    }

    public CandidateEmployeeDto getByName(String firstName ,String LastName){
        return candidateEmployeRepository.getId(firstName, LastName);
    }

    public List<CandidateEmployeeDto> getApplicationJob(String firstName, String lastName){
        List<CandidateEmployeeDto> getFlatData = candidateEmployeRepository.getApplication(firstName, lastName);

        Map<Integer, List<CandidateEmployeeDto>> grouped = getFlatData.stream().collect(Collectors.groupingBy(f -> f.getId()));

        List<CandidateEmployeeDto> result = new ArrayList<>();

        for( var entry : grouped.entrySet()){
            Integer jobId = entry.getKey();
            List<CandidateEmployeeDto> items = entry.getValue();

            String jobName = items.get(0).getJob();

            List<RoadmapJobVacancyDto> roadmap = items.stream()
                .map(i -> new RoadmapJobVacancyDto(i.getAction(),i.getSubmit_date()))
                .collect(Collectors.toList());

            result.add(new CandidateEmployeeDto(jobId,jobName,roadmap));
        }
        return result ;
    }

    public CandidateEmployeeDto getProfile(String username){
        return candidateEmployeRepository.getCandidateProfile(username);
    }

    public boolean save(CandidateEmployeeDto candidateEmployeeDto){
        try {
            CandidateEmployee candidateEmployee = new CandidateEmployee();
            if (candidateEmployeeDto.getId() != null) {
                candidateEmployee.setId(candidateEmployeeDto.getId());
            }
            candidateEmployee.setFirstName(candidateEmployeeDto.getFirstName());
            candidateEmployee.setLastName(candidateEmployeeDto.getLastName());
            candidateEmployee.setAddress(candidateEmployeeDto.getAddress());
            candidateEmployee.setPhoneNumber(candidateEmployeeDto.getPhoneNumber());
            candidateEmployee.setBirth_date(candidateEmployeeDto.getBirth_date());
            candidateEmployee.setCity_date(candidateEmployeeDto.getCity_date());
            candidateEmployee.setCuriculumVitae(candidateEmployeeDto.getCuriculumVitae());
            candidateEmployee.setPortofolio(candidateEmployeeDto.getPortofolio());

            candidateEmployeRepository.save(candidateEmployee);

            return candidateEmployeRepository.findById(candidateEmployee.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
