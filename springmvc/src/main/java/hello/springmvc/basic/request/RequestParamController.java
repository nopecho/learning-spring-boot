package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("name = {}", name);
        log.info("age = {}", age);

        response.getWriter().write("ok");
    }

    @ResponseBody //@ResponseBody 애노테이션이 붙어있는 컨트롤러 메서드는 리턴값을 바로 응답바디에 보냄
    @RequestMapping("/request-param-v2")
    public String requestParam(
            @RequestParam("username") String name,
            @RequestParam("age") int age
    ){
        log.info("userName={}, userAge={}",name,age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, //매개변수명이랑 파라미터명이랑 같으면 생략 가능
            @RequestParam int age
    ){
        log.info("userName={}, userAge={}",username,age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ //전부 생략도 가능
        log.info("userName={}, userAge={}",username,age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, //@RequestParam(required = true) 디폴트값, 필수로 있어야하는 파라미터 정보라는 뜻( 해당 파라미터가 없이 요청이 들어오면 4xx 에러 )
            @RequestParam(required = false) Integer age){

        log.info("userName={}, userAge={}",username,age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, //defaultValue속성으로 기본값 지정가능
            @RequestParam(required = false, defaultValue = "100") Integer age){

        log.info("userName={}, userAge={}",username,age);

        return "ok";
    }

}
