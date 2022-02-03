package nopecho.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * HandlerExceptionResolver로 핸들러 메서드의 예외처리를 처리가능.
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            if(ex instanceof IllegalArgumentException){
                log.info("IlleagalArgumentException to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,ex.getMessage());
                return new ModelAndView();
//                return null; => return null이면 예외를 해결하지 않고 기존 예외를 WAS까지 전달
            }
        }catch (IOException e){
            log.info("resolver ex",e);
        }
        return null;
    }
}
