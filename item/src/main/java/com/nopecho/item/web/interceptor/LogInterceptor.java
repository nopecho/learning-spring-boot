package com.nopecho.item.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 *
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID,uuid);

        //@RequestMapping => HandlerMethod 핸들러 넘어옴
        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler; //호출되는 HandlerMethod의 모든 정보가 담겨있음
        }
        log.info("INTERCEPTOR REQUEST [{}] [{}] [{}]",uuid,requestURI,handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("INTERCEPTOR postHandle [{}]",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        Object uuid = request.getAttribute(LOG_ID);

        log.info("INTERCEPTOR RESPONSE [{}] [{}] [{}]",uuid,requestURI,handler);
        if( ex != null){
            log.error("afterCompletion error!! [{}]",ex);
        }
    }
}
