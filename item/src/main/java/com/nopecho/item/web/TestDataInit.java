package com.nopecho.item.web;

import com.nopecho.item.domain.item.ItemRepository;
import com.nopecho.item.domain.member.Member;
import com.nopecho.item.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        Member member = new Member();
        member.setLoginId("testID");
        member.setPassword("test Password");
        member.setName("테스트");
        memberRepository.save(member);
    }
}
