package com.nopecho.item.web.session;

import com.nopecho.item.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void 세션_생성(){

        //세션 생성 -> 응답 객체에 쿠키로 sessionId 저장 (서버 => 클라이언트)
        MockHttpServletResponse response = new MockHttpServletResponse(); //테스트용 response
        Member member = new Member();
        sessionManager.createSession(member,response);

        //요청에 응답 쿠키 저장 (클라이언트 => 서버)
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션 조회
        Member findMember = (Member) sessionManager.getSession(request);
        assertThat(member).isEqualTo(findMember);

        //세션 만료
        sessionManager.expire(request);
        Object expire = sessionManager.getSession(request);
        assertThat(expire).isNull();
    }
}
