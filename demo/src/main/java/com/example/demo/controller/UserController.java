package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id){
        User user = service.findByID(id);
        return user;
    }

    @GetMapping("/user")
    public User saveUser(@ModelAttribute UserDto userDto){
        log.info("user = {}",userDto.getUserName());
        User save = service.save(userDto.toEntity());
        return save;
    }

}
