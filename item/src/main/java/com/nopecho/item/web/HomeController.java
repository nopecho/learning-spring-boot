package com.nopecho.item.web;

import com.nopecho.item.domain.member.Member;
import com.nopecho.item.domain.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/")
    public String home2(@CookieValue(name = "memberId",required = false)Long memberId, Model model){
        if(memberId==null){
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}
