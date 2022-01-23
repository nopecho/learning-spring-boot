package nopecho.servlet.web.frontcontroller.v1;

import nopecho.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import nopecho.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import nopecho.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerServletV1",urlPatterns = "/front-controller/v1/*") // url경로에 "*"이면 어떤 url이 들어와도 해당 서블릿 호출
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>(); //Controller Mapping 정보담을 해쉬맵 (매핑정보)

    public FrontControllerServletV1() { //생성자로 Mapping될 controller들을 매핑정보에 저장
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); //요청 HTTP에서 URI정보를 꺼냄

        ControllerV1 controller = controllerV1Map.get(requestURI);

        if (controller == null){ //request에서 꺼낸 URI가 매핑정보에 없을시, 예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404응답으로 리턴함
            return;
        }
        controller.process(request,response);
    }
}
