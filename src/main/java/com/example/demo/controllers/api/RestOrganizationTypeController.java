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
import com.example.demo.models.dto.OrganizationTypeDto;
import com.example.demo.services.OrganizationTypeService;

@RestController
@RequestMapping("api/oragnization-type")
public class RestOrganizationTypeController {
    
    private OrganizationTypeService organizationTypeService;

    @Autowired
    public RestOrganizationTypeController(OrganizationTypeService organizationTypeService){
        this.organizationTypeService = organizationTypeService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllData(@RequestHeader(name = "token") String token){
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, organizationTypeService.getAll());
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailOrganization(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, organizationTypeService.getById(id));
    }

    @GetMapping("delete")
    public ResponseEntity<Object> deleteObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, organizationTypeService.remove(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token,@RequestBody OrganizationTypeDto organizationTypeDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
       if (organizationTypeDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    organizationTypeService.save(organizationTypeDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                organizationTypeService.save(organizationTypeDto));
    }
}
