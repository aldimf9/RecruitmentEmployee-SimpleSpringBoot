package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dto.JobVacancyDto;
import com.example.demo.services.JobVacancyService;

@RestController
@RequestMapping("api/job-vacancy")
public class RestJobVacancyController {
    private JobVacancyService jobVacancyService;

    @Autowired
    public RestJobVacancyController(JobVacancyService jobVacancyService) {
        this.jobVacancyService = jobVacancyService;
    }

    @GetMapping("job")
    public ResponseEntity<Object> getAllData(@RequestHeader(name = "token") String token) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, jobVacancyService.getAll());
    }

    @GetMapping
    public ResponseEntity<Object> getAllDataForUser(@RequestHeader(name = "token") String token) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, jobVacancyService.getAllForUser());
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDataById(@RequestHeader(name = "token") String token,
            @RequestParam("id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, jobVacancyService.getById(id));
    }

    @GetMapping("job-detail")
    public ResponseEntity<Object> getDataByIdForUser(@RequestHeader(name = "token") String token,
            @RequestParam("id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, jobVacancyService.getByIdForUser(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestHeader(name = "token") String token,
            @RequestBody JobVacancyDto jobVacancyDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        if (jobVacancyDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK, jobVacancyService.save(jobVacancyDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED, jobVacancyService.save(jobVacancyDto));
    }

}
