package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS) //request Scope 빈의 생명주기는 http요청이 오면 빈이 생성되고 요청이 끊기면 소멸됨
//proxyMode = 가짜 프록시 객체를 만들어서 스프링 빈에 미리 등록 시켜놓음, 그리고 실제 해당 객체를 사용시 실제 객체의 로직을 호출
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String msg){
        System.out.println("["+uuid+"]["+requestURL+"]"+msg);
    }

    @PostConstruct //빈 생성시 출력
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create "+this);

    }

    @PreDestroy //빈 소멸시 출력
    public void close(){
        System.out.println("["+uuid+"] request scope bean destroy "+this);
    }
}
