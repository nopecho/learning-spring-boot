package com.example.demo.ex.service;

import com.example.demo.ex.entity.User;
import com.example.demo.ex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByID(Long id){
        Optional<User> findUser = userRepository.findById(id);
        return findUser.orElse(null);
    }

    public User save(User user){
        userRepository.save(user);
        return user;
    }
}
