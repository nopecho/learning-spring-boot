package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String header(
            HttpServletRequest request, //HttpServletRequest 객체 정보 (서블릿 컨테이너가 만들어주는것)
            HttpServletResponse response, //HttpServletResponse 객체 정보
            HttpMethod method, //Http의 요청 메소드 정보 반환
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap, //http요청 header의 값을 key:value로 받음
            @RequestHeader("host") String host, //http요청 header의 host정보
            @CookieValue(value = "moCookie", required = false) String cookie //http요청 header의 cookie정보
            ){

        log.info("request = {}",request);
        log.info("response = {}", response);
        log.info("method = {}", method);
        log.info("locale = {}", locale);
        log.info("headerMap = {}", headerMap);
        log.info("host = {}", host);
        log.info("cookie = {}", cookie);

        return "ok";
    }
}
