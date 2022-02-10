package com.example.demo.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String bookingName;

    @Column(nullable = false)
    private String bookingPhone;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false, length = 10)
    private int personCount;
}
