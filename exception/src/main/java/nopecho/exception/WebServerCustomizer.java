package nopecho.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * SpringBoot가 제공하는 WebServerCustom객체를 implements
 */
@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        //Error Page 정의 => Parameter : (Http 상태코드 or 예외 클래스 , 호출 할 컨트롤러 경로)
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
        ErrorPage errorPageRuntime = new ErrorPage(RuntimeException.class, "/error-page/500");

        //ErrorPage 등록
        factory.addErrorPages(errorPage404,errorPage500,errorPageRuntime);
    }
}
