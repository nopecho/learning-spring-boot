package com.example.demo.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String LoginId;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private String Name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private Auth role;

}
