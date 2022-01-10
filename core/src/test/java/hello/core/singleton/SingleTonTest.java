package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {
    @Test
    @DisplayName("스프링 없는 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴 적용 객체 사용")
    void singletonServiceTest(){
        SingletionService singletionService = SingletionService.getInstance();
        SingletionService singletionService1 = SingletionService.getInstance();

        System.out.println("singletionService1 = " + singletionService1);
        System.out.println("singletionService = " + singletionService);

        assertThat(singletionService).isSameAs(singletionService1);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 =ac.getBean("memberService",MemberService.class);

        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        assertThat(memberService1).isSameAs(memberService2);
    }
}

