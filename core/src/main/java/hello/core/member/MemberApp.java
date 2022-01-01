package hello.core.member;

public class MemberApp {
    public static void main(String[] args) {

        //매인메소드로 테스트
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"MemberA",Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new Member ="+member.getName());
        System.out.println("find Member ="+findMember.getName());
    }
}
