package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("user", userService.getAll());
        return "user/index";
    }

    @GetMapping("form-insert")
    public String formInsert(Model model){
        model.addAttribute("userDto", new UserDto());
        return "user/insert";
    }
    @PostMapping("insert")
    public String insert(UserDto userDto){
        Boolean result = userService.save(userDto);
        if (result) {
            return "redirect:/user";
        }
        return "user/insert";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        UserDto userDto = new UserDto(
            user.getCandidateEmployee().getId(),
            user.getUsername(),
            user.getPassword(),
            user.getRole().getId()
        );
    
        model.addAttribute("userDto", userDto);
        return "user/update";
    }
    @PostMapping("update")
    public String updateUser(UserDto userDto) {
        Boolean result = userService.save(userDto);
        if (result) {
            return "redirect:/user";
        }
        return "redirect:/user/update/"+ userDto.getId();
    }
}
