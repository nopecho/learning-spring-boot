package nopecho.typeconverter;

import nopecho.typeconverter.converter.IntergerToStringConverter;
import nopecho.typeconverter.converter.IpPortToStringConverter;
import nopecho.typeconverter.converter.StringToIntegerConverter;
import nopecho.typeconverter.converter.StringToIpPortConverter;
import nopecho.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Spring Web Mvc 설정 정보 등록 => implements WebMvcConfigurer
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntergerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());
        registry.addFormatter(new MyNumberFormatter());
    }
}
