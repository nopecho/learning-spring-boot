package nopecho.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //status-line
        response.setStatus(HttpServletResponse.SC_OK);

        //response-header
//        response.setHeader("Content-Type","text/plain;charset=utf-8");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-header","hello");

        //heaer util 기능
        response(response);
        cookie(response); //header에 쿠키 넣기
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void response(HttpServletResponse response){ //header 정보 추가 메서드
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse response){ // header in Cookie 정보 추가 메서드
        Cookie cookie = new Cookie("myCookie","hahaha");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FOUND); //상태코드 302로 응답
        response.setHeader("Location","/basic/hello-form.html"); //응답 상태코드가 302면 "Location" 정보의 경로로 리다렉트

//        response.sendRedirect("/basic/hello-form.html"); <- 이렇게도 사용 가능
    }
}
