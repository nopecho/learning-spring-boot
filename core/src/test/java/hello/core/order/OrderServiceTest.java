package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    orderService orderService = new orderServiceImpl();

    @Test
    void 주문생성(){
        //given
        Long memberId = 1L;
        Member member = new Member(memberId,"현준", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId,"아이템",10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
