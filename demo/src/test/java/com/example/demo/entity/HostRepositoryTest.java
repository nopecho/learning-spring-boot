package com.example.demo.entity;

import com.example.demo.project.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class HostRepositoryTest {

    @Autowired
    private HostRepository hostRepository;

    @AfterEach
    void clear(){
        hostRepository.deleteAll();
    }
    @Test
    void 호스트_저장_테스트(){
        //givne
        Host host = Host.builder()
                .LoginId("host1234")
                .password("test1213")
                .name("제주 투어")
                .addr("제주시 제주로 13-2")
                .email("jeju@gmail.com")
                .phone("070-3383-2827")
                .build();
        //when

        Host save = hostRepository.save(host);

        //then
        assertThat(save).isEqualTo(host);
    }

    @Test
    void 호스트_조회_테스트(){
        //givne
        Host host = Host.builder()
                .LoginId("host1234")
                .password("test1213")
                .name("제주 투어")
                .addr("제주시 제주로 13-2")
                .email("jeju@gmail.com")
                .phone("070-3383-2827")
                .role(Auth.HOST)
                .build();
        hostRepository.save(host);
        //when
        Optional<Host> byId = hostRepository.findById(1L);
        Host find = byId.orElse(null);
        //then
        assertThat(find.getRole()).isEqualTo(Auth.HOST);
    }
}
