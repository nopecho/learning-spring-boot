package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request") //request Scope 빈의 생명주기는 http요청이 오면 빈이 생성되고 요청이 끊기면 소멸됨
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
