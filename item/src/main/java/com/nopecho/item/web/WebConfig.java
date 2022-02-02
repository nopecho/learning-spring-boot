package com.nopecho.item.web;

import com.nopecho.item.web.argumentresolver.LoginMemberArgumentResolver;
import com.nopecho.item.web.filter.LogFilter;
import com.nopecho.item.web.filter.LoginCheckFilter;
import com.nopecho.item.web.interceptor.LogInterceptor;
import com.nopecho.item.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration //Bean 설정 정보 애노테이션
public class WebConfig implements WebMvcConfigurer {

    /**
     * SpringMvcInterceptor 사용시 WebConfig Interceptor 정보 등록 => implements WebMvcConfigurer 구현체 등록하고, addInterceprots @Override 해야됨
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**") //addPathPatterns => 해당 interceptor를 적용시킬 url pattern
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // excludePathPatterns => interceptor적용을 안시키는 url pattern

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**" ,
                        "/" ,
                        "/members/add" ,
                        "/login" ,
                        "/logout" ,
                        "/*.ico" ,
                        "/error");
    }

    /**
     * 사용자 정의 ArgumentResolver 정보 등록 => addArgumentResolvers @Override 해야됨
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    /**
     * ServletFilter 사용시 WebConfig설정 정보 빈 등록 ( web.xml)
     */
//    @Bean // Filter 정보를 스프링 Bean으로 등록( 스프링 빈으로 등록하면 WebContext(서블릿 컨테이너가) ApplicationContext(스프링 컨테이너)를 보고 서블릿 컨테이너 초기화시 Filter정보를 가질 수 있음)
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

    //    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
}
