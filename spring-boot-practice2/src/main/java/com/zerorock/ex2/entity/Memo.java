package com.zerorock.ex2.entity;

import lombok.*;
import javax.persistence.*;

@Entity //엔티티 클래스는 반드시 @Entity어노테이션 필수! (엔티티를 위한 클래스라고 선언, 해당 클래스의 인스턴스들이 JPA로 관리되는 엔티티객체)
@Table(name = "tbl_memo") //해당 엔티티 클래스로 데이터베이스의 어떤 테이블로 생성할 것인지에 대한 정보를 담는 어노테이션 @Table
@ToString //lombok의 ToString 메소드 생성 사용
@Getter //Getter 메소드 생성 (lombok)
@Builder //객체를 생성할 수 있게 처리 (lombok)
@AllArgsConstructor //@Builder를 이용하려면 사용해야됨
@NoArgsConstructor //@Builder를 이용하려면 사용해야됨
public class Memo {
    @Id //@Entity 어노테이션이 붙은 클래스는 PrimaryKey에 해당하는 특정 필드를 @Id 어노테이션으로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK를 자동으로 생성하기위함
    private long mno;

    @Column(length = 200, nullable = false)
    private String memoText;

    @Column(length = 200, nullable = false)
    private String memoText2;
}
