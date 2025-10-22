package com.example.demo.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;
import com.example.demo.repositories.CandidateEmployeeRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CandidateEmployeeRepository candidateEmployeRepository;

    @Autowired
    public UserService(UserRepository userRepository,
            RoleRepository roleRepository,
            CandidateEmployeeRepository candidateEmployeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.candidateEmployeRepository = candidateEmployeRepository;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(GrantedAuthority authority){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(authority);
        return grantedAuthorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userRepository.findByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        String roleName = userDto.getRoleName();
        GrantedAuthority authorities = new SimpleGrantedAuthority(roleName);

        return new org.springframework.security.core.userdetails.User(
                userDto.getUsername(),
                userDto.getPassword(),
                Set.of(authorities));
    }

    public List<UserDto> getAllData() {
        return userRepository.getAllData();
    }

    public UserDto getWithId(Integer id) {
        return userRepository.getWithId(id);
    }

    public boolean save(UserDto userDto) {
        try {
            User user = new User();
            user.setId(userDto.getId());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setRole(roleRepository.findById(userDto.getRole()).orElse(null));
            user.setCandidateEmployee(candidateEmployeRepository.findById(userDto.getId()).orElse(null));

            userRepository.save(user);

            return userRepository.findById(user.getId()).isPresent();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
