package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //final이 붙은 필드 변수를 자동으로 생성자 만들어줌(롬북) 그런데 생성자가 하나밖에 없으면 자동으로 스프링이 @Autowired 붙혀줌 (의존성 주입)
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //MyLogger의 생명주기는 Http요청이 오고 응답시까지임. 그러므로 바로 스프링 컨테이너에서 MyLogger 빈을 주입 받을 수 없음! Provider의 지연 조회 기능 활용


    @RequestMapping("log-demo") // /log-demo <로 http요청이 오면 해당 메소드로 매핑됨
    @ResponseBody // 요청에 대한 http응답을 자바객체 -> 응답 body(본문) 객체에 넣어서 응답을 보냄
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("TestID");
        return "OK";
    }
}
