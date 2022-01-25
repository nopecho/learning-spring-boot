package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){ //Controller에서 ModelAndView를 리턴하면 디스패처 서블릿이 리턴받은 ModelAndView객체에서 모델(데이터)와 viewName을 보고 viewResolver가 찾음
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data","hello!!");
        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){ //Controller에서 Model을 파라미터로 받아서 String을 리턴하면 리턴하는 String자체가 viewName이됨 그럼 해당 viewName으로 viewResolver가 실행되서 해당 view가 렌더링됨
        model.addAttribute("data","goooood");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","이렇게 쓰지마세요");
    }
}
