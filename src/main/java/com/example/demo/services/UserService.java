package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public UserService(UserRepository userRepository,
    RoleRepository roleRepository,
    CandidateEmployeeRepository candidateEmployeRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public List<UserDto> getAllData(){
        return userRepository.getAllData();
    }

    public UserDto getWithId(Integer id){
        return userRepository.getWithId(id);
    }

    public boolean save(UserDto userDto){
        try {
            User user = new User();
            User existingUser = new User();
            if (userDto.getId() != null) {
                existingUser = userRepository.findById(userDto.getId()).orElse(null);
                user = existingUser;
            } else {
                
            }

            user.setCandidateEmployee(candidateEmployeRepository.findById(userDto.getId()).orElse(null));
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
