package nopecho.servlet.web.frontcontroller.v3.controller;

import nopecho.servlet.domain.member.Member;
import nopecho.servlet.domain.member.MemberRepository;
import nopecho.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberSaveControllerV3 implements nopecho.servlet.web.frontcontroller.v3.controller.ControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;
    }
}
