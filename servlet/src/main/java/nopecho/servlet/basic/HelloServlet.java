package nopecho.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") //서블릿 애노테이션
public class HelloServlet extends HttpServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); //HTTP요청 쿼리 파라미터 읽어올 수 있음
        System.out.println("username = " + username);
        System.out.println(request.getServletContext());

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello"+ username); //서버에서 보내는 HTTP응답 본문에 담을수 있음 getWriter()
    }
}
