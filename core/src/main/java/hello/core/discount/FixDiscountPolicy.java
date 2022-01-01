package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; //고정 할인율 1000원
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ //회원 등급이 VIP라면
            return discountFixAmount; //할인 1000원 리턴
        }
        return 0;
    }
}
