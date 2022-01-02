package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        //매인메소드로 테스트
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class); //ApplicationContext = 스프링 컨테이너
        MemberService memberService =  h.getBean("memberService",MemberService.class);

        Member member = new Member(1L,"MemberA",Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new Member ="+member.getName());
        System.out.println("find Member ="+findMember.getName());
    }
}
