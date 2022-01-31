package com.nopecho.item.domain.login;

import com.nopecho.item.domain.member.Member;
import com.nopecho.item.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId,String password){
        return memberRepository.findByLoginId(loginId) //Optional객체 api사용
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
