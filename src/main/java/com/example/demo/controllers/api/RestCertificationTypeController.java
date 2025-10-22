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
import com.example.demo.models.dto.CertificationTypeDto;
import com.example.demo.services.CertificationTypeService;

@RestController
@RequestMapping("api/certification-type")
public class RestCertificationTypeController {
    private CertificationTypeService certificationTypeService;

    @Autowired
    public RestCertificationTypeController(CertificationTypeService certificationTypeService){
        this.certificationTypeService = certificationTypeService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllData(@RequestHeader(name = "token") String token){
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, certificationTypeService.getAll());
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailCertification(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, certificationTypeService.getById(id));
    }

    @GetMapping("delete")
    public ResponseEntity<Object> deleteObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, certificationTypeService.remove(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token, CertificationTypeDto certificationTypeDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
       if (certificationTypeDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    certificationTypeService.save(certificationTypeDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                certificationTypeService.save(certificationTypeDto));
    }


}
