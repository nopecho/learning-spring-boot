package nopecho.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //ComponentScan으로 스프링빈으로 해당 클래스를 빈으로 등록됨, 스프링MVC에서 핸들러매핑에서 매핑정보로 인식할 수 있게 한다.
public class SpringMemberControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
