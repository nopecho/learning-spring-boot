package nopecho.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Slf4j
@Controller
public class ServerltExController {

    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 메시지1");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("hello","이거 꺼낼 수 있냐?");
        response.sendError(404,"404오류 입니다!");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500,"500오류 입니다!");
    }

    @GetMapping("/error-302")
    public void error302(HttpServletResponse response) throws IOException {
        response.sendRedirect("/error-404");
    }
}
