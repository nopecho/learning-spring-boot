package nopecho.servlet.web.frontcontroller.v3.controller;

import nopecho.servlet.domain.member.Member;
import nopecho.servlet.domain.member.MemberRepository;
import nopecho.servlet.web.frontcontroller.ModelView;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements nopecho.servlet.web.frontcontroller.v3.controller.ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);

        return mv;
    }
}
