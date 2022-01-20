package nopecho.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloDate { //JSON데이터를 받아서 변환할 객체
    private String name;
    private int age;
}
