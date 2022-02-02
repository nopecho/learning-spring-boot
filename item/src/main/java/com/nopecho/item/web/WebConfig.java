package com.nopecho.item.web;

import com.nopecho.item.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration //Bean 설정 정보 애노테이션
public class WebConfig {

    @Bean // Filter 정보를 스프링 Bean으로 등록( 스프링 빈으로 등록하면 WebContext(서블릿 컨테이너가) ApplicationContext(스프링 컨테이너)를 보고 서블릿 컨테이너 초기화시 Filter정보를 가질 수 있음)
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
}
