package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="id")
    private Integer id;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name="role",referencedColumnName="id")
    private Role role;

    @OneToOne
    @MapsId
    @JoinColumn(name="id")
    private CandidateEmployee candidateEmployee;
}

/*
 * id
 * username
 * password
 * role
 */