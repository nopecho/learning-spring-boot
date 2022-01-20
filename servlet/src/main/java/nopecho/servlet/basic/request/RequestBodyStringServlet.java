package nopecho.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //HTTP메시지 바디의 정보를 바이트코드로 가져옴 .getInputStream()
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스프링에서 제공하는 바이트코드 형변환 api
        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
