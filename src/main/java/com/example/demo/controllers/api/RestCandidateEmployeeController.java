package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.services.CandidateEmployeeService;

@RestController
@RequestMapping("api/candidate-employee")
public class RestCandidateEmployeeController {
    private CandidateEmployeeService candidateEmployeeService;

    @Autowired
    public RestCandidateEmployeeController(CandidateEmployeeService candidateEmployeeService) {
        this.candidateEmployeeService = candidateEmployeeService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllData(@RequestHeader(name = "token") String token) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, candidateEmployeeService.getAll());
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailData(@RequestHeader(name = "token") String token,
            @RequestParam("id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, candidateEmployeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestHeader(name = "token") String token,
            CandidateEmployeeDto candidateEmployeeDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        if (candidateEmployeeDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    candidateEmployeeService.save(candidateEmployeeDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                candidateEmployeeService.save(candidateEmployeeDto));
    }
}
