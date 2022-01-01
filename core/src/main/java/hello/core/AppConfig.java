package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemmoryMemberRepository;
import hello.core.order.orderService;
import hello.core.order.orderServiceImpl;

public class AppConfig { //AppConfig클래스에서 애플리케이션의 실제 동작에 필요한 구현체 생성 후 주입해줌, DI(의존관계 주입)

    public MemberService memberService(){
        return new MemberServiceImpl(new MemmoryMemberRepository());
    }

    public orderService orderService(){
        return new orderServiceImpl(new MemmoryMemberRepository(),new FixDiscountPolicy());
    }
}
