package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemmoryMemberRepository;
import hello.core.order.orderService;
import hello.core.order.orderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링 애플리케이션 설정 정보 어노테이션
public class AppConfig { //AppConfig클래스에서 애플리케이션의 실제 동작에 필요한 구현체 생성 후 주입해줌, DI(의존관계 주입)

    @Bean //스프링 컨테이너(빈 컨테이너 로 관리)
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemmoryMemberRepository();
    }

    @Bean
    public orderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new orderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("call AppConfig.discountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 할인정책 변경
    }
}
