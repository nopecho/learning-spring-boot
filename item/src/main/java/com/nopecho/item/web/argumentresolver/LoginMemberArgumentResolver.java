package com.nopecho.item.web.argumentresolver;

import com.nopecho.item.domain.member.Member;
import com.nopecho.item.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 사용자 지정 애노테이션이 ArgumentResolver에 등록 될 수 있게 설정하는 클래스
 */
@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("핸들러 메서드 ArgumentResolver supportsParameter 동작");

        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(Login.class);
        log.info("애노테이션 정보 = {}", parameter.getAnnotatedElement());

        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        log.info("애노테이션의 클래스 정보 = {}", parameter.getParameterType());

        return hasParameterAnnotation && hasMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("ArgumentResolver의 resolve 실행!");

        HttpServletRequest nativeRequest = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = nativeRequest.getSession(false);
        if(session == null){
            return null;
        }
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
