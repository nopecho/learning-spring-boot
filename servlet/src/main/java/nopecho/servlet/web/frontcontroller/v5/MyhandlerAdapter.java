package nopecho.servlet.web.frontcontroller.v5;

import nopecho.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyhandlerAdapter { //어댑터

    boolean supports(Object handler); //컨트롤러가 넘어왔을때 해당 컨트롤러를 지원 가능한가 판별하는 메서드

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
