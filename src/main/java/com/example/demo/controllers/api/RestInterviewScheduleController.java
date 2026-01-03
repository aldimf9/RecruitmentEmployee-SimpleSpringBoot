package com.example.demo.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dto.InterviewScheduleDto;
import com.example.demo.services.InterviewScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/interview")
@RequiredArgsConstructor
public class RestInterviewScheduleController {

    private final InterviewScheduleService interviewScheduleService;

    @GetMapping("/interviewer-schedule")
    public ResponseEntity<Object> getInterviewerScheduleData(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK,
                interviewScheduleService.getDetailByInterviewer(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertInterviewScheduleData(@RequestHeader(name = "token") String token, InterviewScheduleDto interviewScheduleDto){
         if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK,
                interviewScheduleService.InsertSchedule(interviewScheduleDto));
    }

}
