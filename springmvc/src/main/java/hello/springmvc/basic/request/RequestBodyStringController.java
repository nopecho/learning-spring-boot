package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream(); //requsetBody의 데이터를 inputStream으로 읽어옴
        String massageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //Stream To String

        log.info("massageBody = {}", massageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException { //인풋스프림, Writer를 파라미터로 바로 받을 수 있음
        String massageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("massageBody = {}", massageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity : Http header,body 정보를 편리하게 조회할 수 있다. (응답도 가능)
     * - 메시지 바디 정보를 직접 조회
     * - 요청 파라미터를 정보하는 기능과 관계 없음
     * <p>
     * 좀더 요청과 응답에 특화된 HttpEntity를 상속받은 RequestEntity, ResponseEntity도 사용 가능
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity requestBodyStringV3(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody(); //HttpEntity로부터 이미 String으로 변환된 body정보 가져옴
        log.info("massageBody = {}", body);
        return new HttpEntity<>("ok");
    }

    @ResponseBody //응답 결과(return값)을 응답 바디에 바로 담아서 응답
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) { //@RequestBody : 스프링이 요청바디 데이터를 읽어서 (메시지 컨버터를 통해) 파라미터로 넘겨줌
        log.info("massageBody = {}", messageBody);
        return "ok";
    }
}
