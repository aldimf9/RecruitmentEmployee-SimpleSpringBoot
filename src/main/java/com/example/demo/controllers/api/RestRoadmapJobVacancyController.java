package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dto.RoadmapJobVacancyDto;
import com.example.demo.services.RoadmapJobVacancyService;

@RestController
@RequestMapping("api/roadmap-job")
public class RestRoadmapJobVacancyController {
    private RoadmapJobVacancyService roadmapJobVacancyService;

    @Autowired
    public RestRoadmapJobVacancyController(RoadmapJobVacancyService roadmapJobVacancyService){
        this.roadmapJobVacancyService = roadmapJobVacancyService;
    }

    @GetMapping()
    public ResponseEntity<Object> getApplyByUser(@RequestHeader(name = "token") String token,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, roadmapJobVacancyService.getAllApply(userDetails.getUsername()));
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailRoadmap(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id,@AuthenticationPrincipal UserDetails userDetails) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, roadmapJobVacancyService.getApplyDetailById(userDetails.getUsername(),id));
    }

    @PostMapping("apply")
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token, RoadmapJobVacancyDto roadmapJobVacancyDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
       if (roadmapJobVacancyDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    roadmapJobVacancyService.save(roadmapJobVacancyDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                roadmapJobVacancyService.save(roadmapJobVacancyDto));
    }

}
