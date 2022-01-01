package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("Vip는 10% 할인이 적용 되어야 한다!")
    void vip_o(){
        //given
        Member member = new Member(1L,"nopecho", Grade.VIP);

        //when
        int discount = rateDiscountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인율 적용이 안된다")
    void vip_x(){
        //given
        Member member = new Member(1L,"nopecho", Grade.BASIC);

        //when
        int discount = rateDiscountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}