package hello.core.member;

public interface MemberRepository {
    void svae(Member member);

    Member findById(Long memberId);
}
