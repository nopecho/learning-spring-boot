package nopecho.servlet.web.frontcontroller.v1.controller;

import nopecho.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; // 컨트롤러(서블릿)에서 뷰(JSP)로 넘겨줄 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //컨트롤러 ->  뷰 로 이동시키는 디스패쳐
        dispatcher.forward(request,response); //forward -> 다른 서블리이나 JSP로 이동할 수 있는 API(서버 내부 호출)
    }
}
