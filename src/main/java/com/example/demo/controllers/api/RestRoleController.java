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
import com.example.demo.models.dto.RoleDto;
import com.example.demo.services.RoleService;

@RestController
@RequestMapping("api/role")
public class RestRoleController {
    private final RoleService roleService;

    @Autowired
    public RestRoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Object> getById(@RequestHeader(name = "token") String token, @RequestParam(name="id") Integer id){
        if (!token.equals("")) {
            if (!token.equals("abc")) {
                return ResponseHandler.generateResponse("your token is not valid", HttpStatus.UNAUTHORIZED,"");
            } else {
                
                return ResponseHandler.generateResponse("success", HttpStatus.OK,roleService.getById(id));
            }
        }
        return ResponseHandler.generateResponse("failed", HttpStatus.BAD_REQUEST,"");
    }
    
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RoleDto roleDto){
        if (roleDto.getId() != null) {
            return ResponseHandler.generateResponse("success", HttpStatus.OK, roleService.save(roleDto));
        }
        return ResponseHandler.generateResponse("success", HttpStatus.CREATED, roleService.save(roleDto));
    }
}
