package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImpl();
    @Test
    void 가입(){
        //given (~이런게 주어 지면)
        Member member = new Member(1L,"noepcho",Grade.VIP);
        //when (~이렇게 되고)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then (~ 이런 결과가 나온다)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
