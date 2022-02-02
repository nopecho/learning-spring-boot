package com.nopecho.item.web;

import com.nopecho.item.domain.member.Member;
import com.nopecho.item.domain.member.MemberRepository;
import com.nopecho.item.web.argumentresolver.Login;
import com.nopecho.item.web.session.SessionManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String home() {
        return "home";
    }

    //    @GetMapping("/")
    public String home2(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //    @GetMapping("/")
    public String home3(HttpServletRequest request, Model model) {
        Member member = (Member) sessionManager.getSession(request); //세션 정보가 없으면 로그인하지 않은 사용자로 분류

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    //    @GetMapping("/")
    public String home4(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false); //request.getSession()의 파라미터로 false하면 세션이 없어도 안만듬
        if (session == null) { // 세션정보가 없으면 홈 화면으로
            return "home";
        }
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER); //session.getAttribute로 세션의 저장된 객체 꺼냄

        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String home5(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member , Model model) {
        if (member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeV6ArgumentResolver(@Login Member member , Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}
