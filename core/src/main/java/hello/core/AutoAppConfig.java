package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member", //basePackages : 컴포넌트 스캔의 스캔 시작 위치 설정 가능
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) //컴포넌트 스캔시 필터링
)
public class AutoAppConfig {
}
