package hello.core.singleton;

public class SingletionService {

    private static final SingletionService instance = new SingletionService();

    public static SingletionService getInstance(){ //해당 객체의 인스턴스는 딱 하나만 생성됨
        return instance;
    }

    private SingletionService(){ //생성자를 private으로 막음
    }

    public void logic(){
        System.out.println("로직 실행");
    }
}
