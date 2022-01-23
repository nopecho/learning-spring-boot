package nopecho.servlet.web.springmvc.v2;

import nopecho.servlet.domain.member.Member;
import nopecho.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members") //Class레벨에 붙어있는 RequestMapping("경로")으로 공통 url처리 가능
public class SpringMemberControllerV2 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView form(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member",member); //ModelAndView에서 model에 값 저장
        return mv;
    }

    @RequestMapping
    public ModelAndView members(){
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.getModel().put("members",members);
        return mv;
    }
}
