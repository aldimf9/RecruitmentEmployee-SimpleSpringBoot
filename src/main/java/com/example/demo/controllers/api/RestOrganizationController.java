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
import com.example.demo.models.dto.OrganizationDto;
import com.example.demo.services.OrganizationService;

@RestController
@RequestMapping("api/organization")
public class RestOrganizationController {
    private OrganizationService organizationService;

    @Autowired
    public RestOrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDataByUser(@RequestHeader(name = "token") String token,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, organizationService.getAll(userDetails.getUsername()));
    }

    @GetMapping("detail")
    public ResponseEntity<Object> getDetailCertification(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, organizationService.getById(id));
    }

    @GetMapping("delete")
    public ResponseEntity<Object> deleteObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, organizationService.remove(id));
    }

    @PostMapping
    public ResponseEntity<Object> insertObject(@RequestHeader(name = "token") String token,
            @RequestParam(name = "user_id") Integer user_id, OrganizationDto organizationDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        if (organizationDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK,
                    organizationService.save(organizationDto));
        }
        organizationDto.setCandidateEmployee(user_id);
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED,
                organizationService.save(organizationDto));
    }
}
