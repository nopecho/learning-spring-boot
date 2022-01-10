package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.orderService;
import hello.core.order.orderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("스프링 컨테이너는 싱글톤으로 관리 된다")
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        orderServiceImpl orderService = ac.getBean("orderService", orderServiceImpl.class);
        MemberRepository memberRepository2 = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository1 = orderService.getMemberRepository();

        System.out.println("orderService -> memberRepository = " + memberRepository1);
        System.out.println("memberService -> memberRepository = " + memberRepository);
        System.out.println("memberRepository -> memberRepository = " + memberRepository2);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository1);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository1);
    }
}
