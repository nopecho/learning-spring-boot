package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
@RestController //@RestController가 있으면 해당 클래스의 메서드는 전부 @ResponseBody 애노테이션이 있는 효과
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1") //HttpServletResponse 객체 사용
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok String");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() { //ReponseEntity 객체 사용
        return new ResponseEntity<>("goood Response Entity",HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){ //@ResponseBody 애노테이션으로 바로 바디에 응답
        return "String 바로 리턴";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){ //ResponseEntity로 json타입 응답
        HelloData helloData = new HelloData();
        helloData.setAge(10);
        helloData.setUsername("hello data");
        return new ResponseEntity<>(helloData,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK) //ResponseBody처럼 애노테이션으로 응답코드도 바로 응답으로 보냄
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setAge(10);
        helloData.setUsername("hello data");
        return helloData;
    }
}
