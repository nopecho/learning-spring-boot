package com.nopecho.item.web;

import com.nopecho.item.domain.member.Member;
import com.nopecho.item.domain.member.MemberRepository;
import com.nopecho.item.web.session.SessionManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home(){
        return "home";
    }

//    @GetMapping("/")
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

    @GetMapping("/")
    public String home3(HttpServletRequest request, Model model){
        Member member = (Member)sessionManager.getSession(request); //세션 정보가 없으면 로그인하지 않은 사용자로 분류

        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}
