package nopecho.servlet.web.springmvc.v3;

import nopecho.servlet.domain.member.Member;
import nopecho.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String form(){
        return "new-form";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model){

        Member member = new Member(username,age);
        memberRepository.save(member);
        model.addAttribute("member",member);
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}
