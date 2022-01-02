package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.orderService;
import hello.core.order.orderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        orderService orderService =appConfig.orderService();

        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = h.getBean("memberService",MemberService.class);
        orderService orderService = h.getBean("orderService", hello.core.order.orderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order oder = orderService.createOrder(memberId,"itemA",20000);

        System.out.println(oder);
    }
}
