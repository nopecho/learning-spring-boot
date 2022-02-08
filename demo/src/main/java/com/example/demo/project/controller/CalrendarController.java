package com.example.demo.project.controller;

import com.example.demo.project.controller.dto.BookingDto;
import com.example.demo.project.domain.Booking;
import com.example.demo.project.domain.BookingRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CalrendarController {

    private final BookingRepository bookingRepository;

    @GetMapping("/calendar/view")
    public String calendarView(@ModelAttribute BookingDto bookingDto){
        return "project/fullcalendar";
    }

    @ResponseBody
    @PostMapping("/calendar/view")
    public Booking save(@ModelAttribute BookingDto bookingDto){
        Booking save = bookingRepository.save(bookingDto.toEntity());
        return save;
    }
}
