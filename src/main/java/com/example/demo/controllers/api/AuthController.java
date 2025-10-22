package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.UserDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.CandidateEmployeeService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CandidateEmployeeRepository candidateEmployeeRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority)
                .orElse(null);
        return jwtUtils.generateToken(userDetails.getUsername(), role);
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody UserDto userDto, CandidateEmployeeDto candidateEmployeeDto) {

        if (userRepository.existsByUsername(userDto.getUsername())) {
            return "Error: Username is already taken!";
        }
        
        CandidateEmployeeService candidateEmployeeService = new CandidateEmployeeService(candidateEmployeeRepository);
        if (candidateEmployeeRepository.existsByFirstName(candidateEmployeeDto.getFirstName())
                && candidateEmployeeRepository.existsByLastName(candidateEmployeeDto.getLastName())) {
            return "Error: Candidate Employee is already taken!";
        }
        // Create new candidate employee
        candidateEmployeeService.save(candidateEmployeeDto);

        UserService userService = new UserService(userRepository, roleRepository, candidateEmployeeRepository);
        // Create new user's account
        String hashedPassword = encoder.encode(userDto.getPassword());
        userDto.setPassword(hashedPassword);
        userService.save(userDto);

        return "User registered successfully!";
    }
}
