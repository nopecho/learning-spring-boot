package nopecho.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import nopecho.servlet.basic.HelloDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloDate helloDate = new HelloDate(); // <- 자바 객체
        helloDate.setAge(20);
        helloDate.setName("chocho");

        String result = objectMapper.writeValueAsString(helloDate); // <- jackson ObjectMapper객체로 자바 객체를 Json으로 바꿈

        response.getWriter().write(result);
    }
}
