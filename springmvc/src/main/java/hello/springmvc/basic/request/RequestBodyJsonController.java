package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper(); //jackson라이브러리 객체 <- -> JSON 변환지원

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",body);

        HelloData helloData = objectMapper.readValue(body, HelloData.class); //objectMapper.readValue로 json으로 온 요청 데이터를 읽어서 HelloData객체 타입으로 변환
       log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

      response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String body) throws IOException {
        log.info("messageBody = {}",body);

        HelloData helloData = objectMapper.readValue(body, HelloData.class); //objectMapper.readValue로 json으로 온 요청 데이터를 읽어서 HelloData객체 타입으로 변환
       log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) { //Json데이터 요청을 받을때 @RequestBody애노테이션이 없으면 해당 파라미터로 들어온 객체는 @ModelAttribute로 동작(요청 파라미터에서 값을 꺼내려고 동작)
       log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public HttpEntity requestBodyJsonV4(RequestEntity<HelloData> httpEntity){ //HttpEntity로도 조회 가능(HttpEntity : HttpRequest , HttpResponse 의 헤더,본문 데이터를 가지고 있는 독립적인 객체
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return new ResponseEntity("ok http",HttpStatus.OK);
    }

    @ResponseBody // 객체 -> Http 메세지 컨버터 -> Json
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data){ //Json -> Http 메세지 컨버터 -> 객체
        HelloData returnData = new HelloData();
        returnData.setUsername("good");
        returnData.setAge(data.getAge()+10);
        data.setAge(data.getAge()+99);
        log.info("username={}, age={}",data.getUsername(),data.getAge());
        return data;
    }
}
