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
import com.example.demo.models.dto.ProjectTypeDto;
import com.example.demo.services.ProjectTypeService;

@RestController
@RequestMapping("api/project-type")
public class RestProjectTypeController {
    
    private ProjectTypeService projectTypeService;

    @Autowired
    public RestProjectTypeController(ProjectTypeService projectTypeService){
        this.projectTypeService = projectTypeService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllData(@RequestHeader(name = "token") String token){
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, projectTypeService.getAll());
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailProject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, projectTypeService.getById(id));
    }

    @GetMapping("delete")
    public ResponseEntity<Object> deleteObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, projectTypeService.remove(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token, ProjectTypeDto projectTypeDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
       if (projectTypeDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    projectTypeService.save(projectTypeDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                projectTypeService.save(projectTypeDto));
    }
}
