package hello.core.member;

public interface MemberService {
    void join(Member member); //회원을 저장소에 저장 시킴

    Member findMember(Long memberId); //회원 아이디를 회원 저장소에서 찾아줌
}
