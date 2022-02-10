package com.example.demo.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Host {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String LoginId;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String addr;

    @Column
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column
    private Auth role; //role

    @OneToMany(mappedBy = "host")
    private List<Post> posts = new ArrayList<>();
}
