package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // private final CandidateEmployeeService candidateEmployeeService;

    // @Autowired
    // public CandidateEmployeeController(CandidateEmployeeService candidateEmployeeService){
    //     this.candidateEmployeeService = candidateEmployeeService;
    // }

    // @GetMapping
    // public String getAll(Model model){
    //     model.addAttribute("candidateEmployees", candidateEmployeeService.getAll());
    //     return "candidateEmployee/index";
    // }

    // @GetMapping({"form","form/{id}"})
    // public String form(@PathVariable(required = false) Integer id ,Model model){
    //     if (id != null) {
    //         CandidateEmployeeDto candidateEmployeeDTO = candidateEmployeeService.getById(id);
    
    //         model.addAttribute("candidateEmployeeDTO", candidateEmployeeDTO);
    //         return "candidateEmployee/update";
    //     }
    //     model.addAttribute("candidateEmployeeDTO", new CandidateEmployeeDto());
    //     return "candidateEmployee/form";
    // }
    // @PostMapping("save")
    // public String save(CandidateEmployeeDto candidateEmployeeDTO){
    //     Boolean result = candidateEmployeeService.save(candidateEmployeeDTO);
    //     if (!result && candidateEmployeeDTO.getId() != null) {
    //         return "candidateEmployee/update";
    //     }else if (!result) {
    //         return "candidateEmployee/form";
    //     }
    //     return "redirect:/candidate-employee";
    // }

    // @GetMapping("delete/{id}")
    // public String delete(@PathVariable("id") Integer id) {
    //     Boolean result = candidateEmployeeService.remove(id);
    //     if (result) {
    //         return "redirect:/candidate-employee";
    //     }
    //     return "candidateEmployee/index";
    // }
}
