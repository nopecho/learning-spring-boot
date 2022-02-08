package com.example.demo.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Host {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String hostId;

    @Column(nullable = false, unique = true)
    private String hostPassword;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addr;

    @Column
    private String email;

    @Column(nullable = false)
    private String phone;
}
