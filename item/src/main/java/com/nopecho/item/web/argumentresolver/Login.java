package com.nopecho.item.web.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 사용자 지정 애노테이션 생성
 * @Target : 해당 애노테이션을 어디에서 사용 할건지 ( Method, Class, Parameter 등등)
 * @Retention : 해당 애노테이션 정보를 Java 리플렉션 등에 활용 할 수 있도록 애노테이션 정보 유지 정책
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
}
