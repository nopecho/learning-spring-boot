package nopecho.servlet.web.serlvetmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet",urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet { //Servlet이 컨트롤러 JSP가 뷰 분리

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; // 컨트롤러(서블릿)에서 뷰(JSP)로 넘겨줄 경로
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //컨트롤러 ->  뷰 로 이동시키는 디스패쳐
        dispatcher.forward(request,response); //forward -> 다른 서블리이나 JSP로 이동할 수 있는 API(서버 내부 호출)
    }
}
