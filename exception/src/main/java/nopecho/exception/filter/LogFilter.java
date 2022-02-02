package nopecho.exception.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("FILTER REQUEST [{}] URI=[{}] DispatcherType=[{}]",uuid,requestURI,httpRequest.getDispatcherType());
            chain.doFilter(request,response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("FILTER RESPONSE [{}] URI=[{}] DispatcherType=[{}]",uuid,requestURI,httpRequest.getDispatcherType());
        }
    }
}
