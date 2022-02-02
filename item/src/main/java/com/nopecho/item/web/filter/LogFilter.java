package com.nopecho.item.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log Filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("logFilter doFilter");

        HttpServletRequest httpServlet = (HttpServletRequest) request; //ServletRequest => httpServletRequest 다운 캐스팅
        String requestURI = httpServlet.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}] [{}]",uuid,requestURI);
            chain.doFilter(request,response); //doFilter호출해야 다음 요청으로 넘어감(다음 Filter가 있든 없든)
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}] [{}]",uuid,requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log Filter destroy");
    }
}
