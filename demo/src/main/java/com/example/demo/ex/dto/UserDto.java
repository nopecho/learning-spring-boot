//package com.example.demo.ex.dto;
//
//import com.example.demo.ex.entity.User;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.validation.constraints.NotNull;
//
//@Slf4j
//@Data
//public class UserDto {
//    private String userName;
//    private int age;
//    private String email;
//
//    @NotNull
//    private String role;
//
//    private String addr;
//
//    public User toEntity(){
//        log.info("DTO to ENTITY 호출!");
//        User user = new User();
//        user.setUserName(this.userName);
//        user.setAddr(this.addr);
//        user.setEmail(this.email);
//        user.setRole(this.role);
//        return user;
//    }
//}
