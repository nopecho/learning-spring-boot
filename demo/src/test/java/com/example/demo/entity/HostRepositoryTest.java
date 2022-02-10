package com.example.demo.entity;

import com.example.demo.project.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class HostRepositoryTest {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void clear(){
        hostRepository.deleteAll();
        postRepository.deleteAll();
    }

    @BeforeEach
    void cleare(){
        hostRepository.deleteAll();
        postRepository.deleteAll();
    }
    @Test
    void 호스트_저장_테스트(){
        //givne
        Host host = Host.builder()
                .LoginId("host12334")
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

    @Transactional
    @Test
    void 호스트가_작성한_게시물_조회_테스트(){
        //given
        Host host = Host.builder()
                .LoginId("host123334")
                .password("test1213")
                .name("제주 투어")
                .addr("제주시 제주로 13-2")
                .email("jeju@gmail.com")
                .phone("070-3383-2827")
                .role(Auth.HOST)
                .build();

        hostRepository.save(host);


        Post post = Post.builder()
                .host(host)
                .category(Category.Tour)
                .text("제주 한라산 투어 인원 모집~~!")
                .title("장소 : 제주도 " +
                        "일정은 추후 통보 예정입니다." +
                        "당일 예약만 가능하며 1일 1팀만 받고 있습니다 ㅋㅋ" +
                        "예약하세용")
                .build();


        //when
        Optional<Host> optionalHost = hostRepository.findById(1L);
        Host findHost = optionalHost.orElse(null);
        postRepository.save(post);


        List<Post> posts = postRepository.findByHostId(findHost.getId());
        System.out.println("posts.get(0) = " + posts.get(0));

        //then
        assertThat(posts.get(0).getText()).isEqualTo("제주 한라산 투어 인원 모집~~!");
    }
}
