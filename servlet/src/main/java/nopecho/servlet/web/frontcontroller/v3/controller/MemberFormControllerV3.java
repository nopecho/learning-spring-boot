package nopecho.servlet.web.frontcontroller.v3.controller;

import nopecho.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public class MemberFormControllerV3 implements nopecho.servlet.web.frontcontroller.v3.controller.ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) { //paramMap은 프론트컨트롤러에서 처리후 넘겨받음
        return new ModelView("new-form"); //렌더링할 view(JSP)의 파일명을 가지는 ModelView객체 반환
    }
}
