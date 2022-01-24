package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //lombok이 지원하는 log구현체
@RestController // RestController를 쓰면 Http응답 바디에 return타입을 바로 넣어서 응답함
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); //log사용 slf4j

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        //log 찍을때는 포매팅처럼 파라미터로 넘김
        log.trace("trace log={}",name); //모든 레벨 로그
        log.debug("debug loh={}",name); //디버깅 레벨 로그
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        return "ok";
    }
}
