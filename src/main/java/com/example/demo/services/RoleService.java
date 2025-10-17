package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.dto.RoleDto;
import com.example.demo.repositories.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAll(){
        return roleRepository.getAllData();
    }

    public Role getById(Integer id){
        return roleRepository.findById(id).orElse(null);
    }

    public boolean save(RoleDto roleDto){
        try {
            Role role = new Role();
            if (roleDto.getId() != null) {
                role.setId(roleDto.getId());    
            }
            role.setName(roleDto.getName());

            roleRepository.save(role);

            return roleRepository.findById(role.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Integer id){
        roleRepository.deleteById(id);
        return !roleRepository.findById(id).isPresent();
    }
}
