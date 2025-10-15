package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Role;
import com.example.demo.models.dto.RoleDto;
import com.example.demo.services.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
    
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("role", roleService.getAll());
        return "role/index";
    }

    @GetMapping("form-insert")
    public String formAddRole(Model model){
        model.addAttribute("roleDto", new RoleDto());
        return "role/insert";
    }
    @PostMapping("insert")
    public String addRole(RoleDto roleDto){
        boolean result = roleService.save(roleDto);
        if (result) {
            return "redirect:/role";
        }
        return "role/insert";
    }

    @GetMapping("form-update/{id}")
    public String formPutRole(@PathVariable("id") Integer id, Model model){
        Role role = roleService.getById(id);
        RoleDto roleDto = new RoleDto(
            role.getId(),
            role.getName()
        );
        model.addAttribute("roleDto", roleDto);
        return "role/update";
    }
    @PostMapping("update")
    public String putRole(RoleDto roleDto){
        boolean result = roleService.save(roleDto);
        if (result) {
            return "redirect:/role";
        }
        return "role/update";
    }
}
