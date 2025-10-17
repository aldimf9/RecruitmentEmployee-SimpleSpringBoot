package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.dto.UserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/user")
public class RestUserController {
    private final UserService userService;
    
    @Autowired
    public RestUserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public UserDto getId(@RequestHeader(name="token") String token,@RequestParam(name="id") Integer id){
        if (!token.equals("")) {
            if (!token.equals("abc")) {
                return null;
            } else {
                return userService.getWithId(id);
        }
        } 
        return null;
    }

    @PostMapping
    public boolean save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
}
