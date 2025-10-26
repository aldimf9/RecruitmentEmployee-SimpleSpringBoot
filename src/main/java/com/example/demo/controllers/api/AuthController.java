package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.User;
import com.example.demo.models.dto.CandidateEmployeeDto;
import com.example.demo.models.dto.RegisterDto;
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
    public ResponseEntity<Object> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority)
                .orElse(null);
        return ResponseHandler.generateResponse("success", HttpStatus.OK, jwtUtils.generateToken(userDetails.getUsername(), role));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterDto registerDto) {
        CandidateEmployeeDto candidateEmployeeDto = new CandidateEmployeeDto(
            null,
            registerDto.getFirstName(),
            registerDto.getLastName(),
            registerDto.getAddress(),
            registerDto.getPhoneNumber(),
            registerDto.getBirthDate(),
            registerDto.getCityDate(),
            registerDto.getCuriculumVitae(),
            registerDto.getPortofolio()
        );
        
        CandidateEmployeeService candidateEmployeeService = new CandidateEmployeeService(candidateEmployeeRepository);
        if (candidateEmployeeRepository.existsByFirstName(candidateEmployeeDto.getFirstName())
                && candidateEmployeeRepository.existsByLastName(candidateEmployeeDto.getLastName())) {
            return ResponseHandler.generateResponse("Error: Candidate Employee is already taken!", HttpStatus.NOT_ACCEPTABLE,null) ;
        }
        // Create new candidate employee
        candidateEmployeeService.save(candidateEmployeeDto);

        candidateEmployeeDto = candidateEmployeeService.getByName(candidateEmployeeDto.getFirstName(), candidateEmployeeDto.getLastName());

        UserDto userDto = new UserDto(
            candidateEmployeeDto.getId(),
            registerDto.getUsername(),
            registerDto.getPassword(),
            11
        );

        if (userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseHandler.generateResponse( "Error: Username is already taken!", HttpStatus.NOT_ACCEPTABLE,null) ;
        }

        UserService userService = new UserService(userRepository, roleRepository, candidateEmployeeRepository);
        // Create new user's account
        String hashedPassword = encoder.encode(userDto.getPassword());
        userDto.setPassword(hashedPassword);
        userService.save(userDto);

        return ResponseHandler.generateResponse("User registered successfully!", HttpStatus.CREATED, null) ;
    }
}
