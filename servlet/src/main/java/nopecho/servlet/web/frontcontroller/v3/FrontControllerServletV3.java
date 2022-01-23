package nopecho.servlet.web.frontcontroller.v3;

import nopecho.servlet.web.frontcontroller.ModelView;
import nopecho.servlet.web.frontcontroller.MyView;
import nopecho.servlet.web.frontcontroller.v2.ControllerV2;
import nopecho.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import nopecho.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import nopecho.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import nopecho.servlet.web.frontcontroller.v3.controller.ControllerV3;
import nopecho.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import nopecho.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import nopecho.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerV3Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();

        MyView view = viewResolver(viewName);

        view.render(mv.getModel() ,request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>(); //HTTP요청 param정보를 담을 HashMap
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
