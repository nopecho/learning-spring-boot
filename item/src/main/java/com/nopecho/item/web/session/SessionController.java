package com.nopecho.item.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Locale;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "session 정보 없음";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name = {}, value = {}",name,session.getAttribute(name)));

        log.info("sessionID = {}",session.getId());
        log.info("sessionMaxInactiveInterval = {}",session.getMaxInactiveInterval());
        log.info("createTime = {}",new Date(session.getCreationTime()));
        log.info("lastAccessedTime = {}",new Date(session.getLastAccessedTime()));
        log.info("is New = {} ",session.isNew());
        log.info("session ServletContext = {}",session.getServletContext());

        return "session 정보 출력";
    }
}
