package nopecho.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import nopecho.servlet.basic.HelloDate;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper= new ObjectMapper(); //스프링에서 제공하는 jackson라이브러리 객체(json객체를 자바 객체로 변환)

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);

        HelloDate helloDate = objectMapper.readValue(messageBody, HelloDate.class); //jackson의 readValue API로 JSON형식의 (데이터, 변환할 자바 객체 클래스) 를 파라미터로 받아서 객체로 변환시켜줌
        System.out.println("helloDate.getName() = " + helloDate.getName());
        System.out.println("helloDate.getAge() = " + helloDate.getAge());

        response.getWriter().write("ok");
    }
}
