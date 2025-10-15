package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CandidateEmployee;
import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;
import com.example.demo.repositories.CandidateEmployeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CandidateEmployeRepository candidateEmployeRepository;

    @Autowired
    public UserService(UserRepository userRepository,
    RoleRepository roleRepository,
    CandidateEmployeRepository candidateEmployeRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean save(UserDto userDto){
        try {
            User existingUser = userRepository.findById(userDto.getId()).orElse(null);
            User user = new User();
            if (existingUser != null) {
                user = existingUser;
            } else {
                 user.setCandidateEmployee(candidateEmployeRepository.findById(userDto.getId()).orElse(null)); 
            }
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setRole(roleRepository.findById(userDto.getRole()).orElse(null));
        
            userRepository.save(user);
        
            return userRepository.findById(user.getId()).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
