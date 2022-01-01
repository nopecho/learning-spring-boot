package hello.core.member;

public interface MemberRepository { //회원저장소 인터페이스
    void svae(Member member); //회원을 저장소에 저장

    Member findById(Long memberId); //회원 저장소에서 회원 아이디로 회원 찾기
}
