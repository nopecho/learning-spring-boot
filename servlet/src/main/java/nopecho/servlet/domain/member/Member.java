package nopecho.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id; //회원 저장할때 식별자로 사용할 id
    private String userName;
    private int age;

    public Member(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public Member() {
    }
}
