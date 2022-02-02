package com.nopecho.item.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> seesionStore = new ConcurrentHashMap<>(); //세션 저장소

    /**
     * 세션 생성
     * -> sessionId 생성( UUID로 임의의 추정 불가능 값 생성 )
     * -> 세션 저장소에 sessionId와 보관 할 값 저장( 보통 DB를 연동해서 쓰지만 예제를 위해 map사용 )
     * -> sessionId로 응답 쿠기를 만들어서 클라이언트에 전달
     */
    public void createSession(Object value, HttpServletResponse response){
        //sessionId 생성
        String sessionId = UUID.randomUUID().toString();
        System.out.println(sessionId);
        //세셩 저장소에 저장
        seesionStore.put(sessionId,value);

        //쿠키 생성
        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null){
            return null;
        }
        return seesionStore.get(sessionCookie.getValue());
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if( sessionCookie != null ){
            seesionStore.remove(sessionCookie.getValue());
        }
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .findAny()
                .orElse(null);
    }
}
