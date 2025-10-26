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
import com.example.demo.models.dto.UserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/user")
public class RestUserController {
    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getId(@RequestHeader(name = "token") String token,
            @RequestParam(name = "id") Integer id) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, null);
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, userService.getWithId(id));
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllUser(@RequestHeader(name = "token") String token) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, null);
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK, userService.getAllData());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestHeader(name = "token") String token, @RequestBody UserDto userDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, null);
        }
        return ResponseHandler.generateResponse("modified success", HttpStatus.OK, userService.save(userDto));
    }
}
