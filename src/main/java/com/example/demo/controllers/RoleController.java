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
        model.addAttribute("roles", roleService.getAll());
        return "role/index";
    }

    @GetMapping({"form","form/{id}"})
    public String formAddRole(@PathVariable(required = false) Integer id,Model model){
        if (id != null) {
            Role role = roleService.getById(id);
            RoleDto roleDto = new RoleDto(
                role.getId(),
                role.getName()
            );
            model.addAttribute("roleDto", roleDto);
            return "role/update";
        }
        model.addAttribute("roleDto", new RoleDto());
        return "role/insert";
    }
    @PostMapping("save")
    public String saveRole(RoleDto roleDto){
        boolean result = roleService.save(roleDto);
        if (!result && roleDto.getId() != null) {
            return "role/update";
        } else if (!result) {
            return "role/insert";
        }
        return "redirect:/role";
    }
}
