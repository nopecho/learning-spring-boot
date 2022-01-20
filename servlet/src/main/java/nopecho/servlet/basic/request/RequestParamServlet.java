package nopecho.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송기능
 * http://localhost:8080/request-param?name=nopecho&age=27
 */
@WebServlet(name = "requestPramServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회 - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 - end");
        System.out.println();

        System.out.println("단일 파라미터 조회 - start");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("단일 파라미터 조회 - start");
        System.out.println();

        System.out.println("파라미터 키 하나에 값 복수일때 조회 - start");
        String[] names = request.getParameterValues("name");
        for (String vlaue : names) {
            System.out.println("name = " + vlaue);
        }
        System.out.println("파라미터 키 하나에 값 복수일때 조회 - end");


    }
}
