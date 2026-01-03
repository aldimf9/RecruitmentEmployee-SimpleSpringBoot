package com.example.demo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UserDto(Integer id, String username, String password, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDto(String username, String password, String roleName) {
        this.username = username;
        this.password = password;
        this.roleName = roleName;
    }

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto(Integer id, String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDto(Integer id , Integer role){
        this.id = id;
        this.role = role;
    }

    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private String roleName;
    private String firstName;
    private String lastName;
}
