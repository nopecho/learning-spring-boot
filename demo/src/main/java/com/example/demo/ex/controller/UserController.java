//package com.example.demo.ex.controller;
//
//import com.example.demo.ex.dto.UserDto;
//import com.example.demo.ex.entity.User;
//import com.example.demo.ex.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService service;
//
//    @GetMapping("/user/{id}")
//    public User findUser(@PathVariable Long id){
//        User user = service.findByID(id);
//        return user;
//    }
//
//    @GetMapping("/user")
//    public User saveUser(@ModelAttribute UserDto userDto){
//        log.info("user = {}",userDto.getUserName());
//        User save = service.save(userDto.toEntity());
//        return save;
//    }
//
//}
