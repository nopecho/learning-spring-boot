package nopecho.servlet.web.frontcontroller.v5;

import nopecho.servlet.web.frontcontroller.ModelView;
import nopecho.servlet.web.frontcontroller.MyView;
import nopecho.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import nopecho.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import nopecho.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import nopecho.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyhandlerAdapter> myhandlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdaprers();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyhandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel() ,request, response);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyhandlerAdapter getHandlerAdapter(Object handler) {
        for (MyhandlerAdapter adapter : myhandlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw  new IllegalArgumentException("Handler Adapter를 찾을 수 없음"+handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private void initHandlerAdaprers() {
        myhandlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }
}
