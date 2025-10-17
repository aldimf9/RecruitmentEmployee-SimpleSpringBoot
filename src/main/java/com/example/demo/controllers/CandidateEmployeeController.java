package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.services.CandidateEmployeeService;

@Controller
@RequestMapping("candidate-employee")
/*
 * get all -> http://localhost:9000/candidateEmployee -> Get
 * get by id -> http://localhost:9000/candidateEmployee/1 -> Get
 * get insert -> http://localhost:9000/candidateEmployee -> Get & Post
 * get remove -> http://localhost:9000/candidateEmployee -> Get / Get & Post
 */
public class CandidateEmployeeController {
    /*
     * Request Get
     * Request Post
     */

    private final CandidateEmployeeService candidateEmployeeService;

    @Autowired
    public CandidateEmployeeController(CandidateEmployeeService candidateEmployeeService){
        this.candidateEmployeeService = candidateEmployeeService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("candidateEmployees", candidateEmployeeService.getAll());
        return "candidateEmployee/index";
    }

    @GetMapping({"form","form/{id}"})
    public String form(@PathVariable(required = false) Integer id ,Model model){
        if (id != null) {
            CandidateEmployee candidate = candidateEmployeeService.getById(id);
            CandidateEmployeeDto candidateEmployeeDTO = new CandidateEmployeeDto(
                candidate.getId(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getAddress(),
                candidate.getPhoneNumber(),
                candidate.getBirth_date(),
                candidate.getCity_date(),
                candidate.getCuriculumVitae(),
                candidate.getPortofolio()
            );
    
            model.addAttribute("candidateEmployeeDTO", candidateEmployeeDTO);
            return "candidateEmployee/update";
        }
        model.addAttribute("candidateEmployeeDTO", new CandidateEmployeeDto());
        return "candidateEmployee/form";
    }
    @PostMapping("save")
    public String save(CandidateEmployeeDto candidateEmployeeDTO){
        Boolean result = candidateEmployeeService.save(candidateEmployeeDTO);
        if (!result && candidateEmployeeDTO.getId() != null) {
            return "candidateEmployee/update";
        }else if (!result) {
            return "candidateEmployee/form";
        }
        return "redirect:/candidate-employee";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Boolean result = candidateEmployeeService.remove(id);
        if (result) {
            return "redirect:/candidate-employee";
        }
        return "candidateEmployee/index";
    }
}
