package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {
    public static void main(String[] args) {

        //매인메소드로 테스트
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L,"MemberA",Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new Member ="+member.getName());
        System.out.println("find Member ="+findMember.getName());
    }
}
