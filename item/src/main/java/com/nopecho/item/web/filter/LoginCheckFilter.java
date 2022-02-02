package com.nopecho.item.web.filter;

import com.nopecho.item.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    //whiteList 정보 => LoginCheckFilter를 거치치 않아도 되는 url List
    private final static String[] wihteList = {"/","/members/add","/login","/logout","/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //HttpServletRequest 정보
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        //HttpSerlvetResponse 정보
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("===LoginCheckFilter Start===");

            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 Filter 시작 = {}",requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미 인증 사용자 요청 = {}",requestURI);

                    httpResponse.sendRedirect("/login?redirectURL="+requestURI); //어느 요청에서 온 건지 확인
                    return; //return 으로 다음 filter나 servlet요청 하지않고 해당 필터 종료
                }
            }
            chain.doFilter(request,response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("===LoginCheckFilter end===");
        }
    }

    /**
     *
     * @param requestURI => 해당 URI가 LoginCheckFilter를 적용하는 URI인지 판단하는 메서드
     * @return
     */
    private boolean isLoginCheckPath(String requestURI){
        //PatternMatchUtils( URI패턴이 특정(파라미터로 들어온 값)값이랑 매칭 되는지 판단하는 유틸 클래스 )
        return !PatternMatchUtils.simpleMatch(wihteList,requestURI);
    }
}
