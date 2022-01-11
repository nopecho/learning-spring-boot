package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //DIP 완성!

    @Autowired //생성자 의존관계 주입 ( ac.getBean(
    public MemberServiceImpl(MemberRepository memberRepository) { //생성자를 통해 외부에서(AppConfig) 생성자를 통해 구현체 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.svae(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
