package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemmoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //클래스 내의 final이 붙어 있는 변수의 생성자 만들어줌
public class orderServiceImpl implements orderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; //DIP를 지키기위해 인터페이스에만 의존하도록 변경, BUT 구현체가 없기때문에 NPE발생!

//    public orderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { //외부에서 생성자를 통해 구현체를 주입해줌
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
