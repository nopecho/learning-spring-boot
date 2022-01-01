package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemmoryMemberRepository;

public class orderServiceImpl implements orderService{

    private final MemberRepository memberRepository = new MemmoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); 
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //할인 정책이 바뀐다면 OrderServiceImpl코드를 고쳐야함

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
