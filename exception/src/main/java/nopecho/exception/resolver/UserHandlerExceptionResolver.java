package nopecho.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nopecho.exception.customexception.UserException;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    // Jacson 라이브러리의 [ 객체 <-> JSON ] 변환 객체
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{
            if(ex instanceof UserException){
                log.info("UserException resolver to 400 Error");
                String accept = request.getHeader("accept"); //requset의 Accept(json,html ...)에 따른 예외 처리
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if(accept.equals(MediaType.APPLICATION_JSON_VALUE)){
                    Map<String, Object> result = new HashMap<>();
                    result.put("ex",ex.getClass());
                    result.put("message",ex.getMessage());

                    String reponseJSON = objectMapper.writeValueAsString(result); //객체 -> JSON

                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    response.getWriter().write(reponseJSON);
                    return new ModelAndView();
                } else {
                    return new ModelAndView("error/404");
                }
            }
        }catch (IOException e){
            log.info("resolver ex",ex);
        }
        return null;
    }
}
