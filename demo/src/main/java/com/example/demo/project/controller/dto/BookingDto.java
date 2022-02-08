package com.example.demo.project.controller.dto;


import com.example.demo.project.domain.Booking;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
public class BookingDto {

    private String booker;

    private String phone;

    private int personnel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    public Booking toEntity(){
        return Booking.builder()
                .booker(this.booker)
                .phone(this.phone)
                .personnel(this.personnel)
                .bookingDate(this.bookingDate)
                .build();
    }
}
