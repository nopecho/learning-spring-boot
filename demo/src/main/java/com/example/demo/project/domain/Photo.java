package com.example.demo.project.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String file;

    @Column(nullable = false)
    private String path;

}
