package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplcationContextBasicFindTest {
    //스프링 ApplicationContext에 Annotation 기반으로 등록 된 스프링 컨테이너 정보를 가져옴 (AppConfig.class 파일에 등록 되어있는 Configration 정보로)
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //when

        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로 조회")
    void findBeanByType(){
        //given
        MemberService memberService = ac.getBean(MemberService.class); //bean이름 없이 타입으로 검사

        //when

        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName(".class 확인")
    void checkByClass(){
        System.out.println(MemberService.class);
        System.out.println(MemberServiceImpl.class);
        MemberService memberService = ac.getBean(MemberService.class);

        System.out.println(memberService.getClass());
        if (memberService instanceof MemberServiceImpl){
            System.out.println("memberService 는 MemberServiceImpl(멤버 서비스의 구현체) 타입이다");
        }

        Method[] methods = MemberService.class.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //given
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); //구체 타입으로 검사 (BUT,구현체에 의존하기보단 추상화에 의존)

        //when

        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 불가")
    void findBeanByNameX(){
//        MemberService x = ac.getBean("x", MemberService.class);

        //junit5 의 Assertions.assertThrows 메소드 활용 ( [예외 종류], [콜백 함수](해당 [콜백함수]에서 [예외종류]가 터져야 테스트 성공) )
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("x", MemberService.class));
    }
}
