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
import com.example.demo.models.dto.ProfesionalDto;
import com.example.demo.services.ProfesionalService;

@RestController
@RequestMapping("api/professional")
public class RestProfesionalController {
    private ProfesionalService profesionalService;

    @Autowired
    public RestProfesionalController(ProfesionalService profesionalService){
        this.profesionalService = profesionalService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDataByUser(@RequestHeader(name = "token") String token,
           @AuthenticationPrincipal UserDetails userDetails) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, profesionalService.getAll(userDetails.getUsername()));
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailProfessional(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, profesionalService.getById(id));
    }

    @GetMapping("delete")
    public ResponseEntity<Object> deleteObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, profesionalService.remove(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "user_id") Integer user_id, ProfesionalDto profesionalDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        if (profesionalDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    profesionalService.save(profesionalDto));
        }
        profesionalDto.setCandidateEmployee(user_id);
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                profesionalService.save(profesionalDto));
    }
}
