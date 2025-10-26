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
import com.example.demo.models.dto.ProfesionalTypeDto;
import com.example.demo.services.ProfesionalTypeService;

@RestController
@RequestMapping("api/professional-type")
public class RestProfesionalTypeController {
    
    private ProfesionalTypeService profesionalTypeService;
    
    @Autowired
    public RestProfesionalTypeController(ProfesionalTypeService profesionalTypeService){
        this.profesionalTypeService = profesionalTypeService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllData(@RequestHeader(name = "token") String token){
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, profesionalTypeService.getAll());
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailProfesional(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, profesionalTypeService.getById(id));
    }

    @GetMapping("delete")
    public ResponseEntity<Object> deleteObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, profesionalTypeService.remove(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token,@RequestBody ProfesionalTypeDto profesionalTypeDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
       if (profesionalTypeDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    profesionalTypeService.save(profesionalTypeDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                profesionalTypeService.save(profesionalTypeDto));
    }
}
