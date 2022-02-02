package nopecho.exception.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID,uuid);

        log.info("INTERCEPTOR REQUEST [{}] URI=[{}] DispatcherType=[{}] handler=[{}]",uuid,requestURI,request.getDispatcherType(),handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("ModelAndView = [{}]",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object uuid = request.getAttribute(LOG_ID);
        String requestURI = request.getRequestURI();

        log.info("INTERCEPTOR RESPONSE [{}] URI=[{}] DispatcherType=[{}] handler=[{}]",uuid,requestURI,request.getDispatcherType(),handler);
        if(ex != null){
            log.info("AfterCompletion Error!!",ex);
        }
    }
}
