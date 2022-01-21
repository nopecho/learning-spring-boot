package nopecho.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clear();
    }

    @Test
    void 저장_테스트(){
        //given
        Member member = new Member("hello",20);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findbyId(member.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void 회원_전체조회(){
        //given
        Member member1 = new Member("member1", 27);
        Member member2 = new Member("member2", 26);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        result.forEach(member -> System.out.println("member = " + member.getUserName()));
        assertThat(result.size()).isEqualTo(2);
    }
}
