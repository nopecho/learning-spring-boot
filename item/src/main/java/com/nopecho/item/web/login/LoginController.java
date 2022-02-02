package com.nopecho.item.web.login;

import com.nopecho.item.domain.login.LoginService;
import com.nopecho.item.domain.member.Member;
import com.nopecho.item.web.SessionConst;
import com.nopecho.item.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginFrom(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

//    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member member = loginService.login(form.getLoginId(), form.getPassword());

        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //쿠키에 시간 정보 x => 세션쿠키 (브라우저 종료시 쿠키 소멸)
        Cookie cookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(cookie);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member member = loginService.login(form.getLoginId(), form.getPassword());

        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        sessionManager.createSession(member,response);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute LoginForm form,
                          BindingResult bindingResult,
                          HttpServletRequest request,
                          @RequestParam(value = "redirectURL",defaultValue = "/") String redirectURL){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member member = loginService.login(form.getLoginId(), form.getPassword());

        if(member == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //HttpServlet이 제공하는 세션기능
        //request에 세션이 있으면 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,member);

        return "redirect:"+redirectURL;
    }

//    @PostMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request){
        extracted(response, Arrays.stream(request.getCookies()).findFirst().get().getName());
        return "redirect:/";
    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if( session != null ){
            session.invalidate(); //세션 삭제
        }
        return "redirect:/";
    }

    private void extracted(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
