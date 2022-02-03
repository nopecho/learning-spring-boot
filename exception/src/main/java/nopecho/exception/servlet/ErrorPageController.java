package nopecho.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ErrorPageController {
    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    @RequestMapping("/error-page/404")
    public String errorpage404(HttpServletRequest request, HttpServletResponse response){
        Object hello = request.getAttribute("hello");
        log.info(hello.toString());
        log.info("ErrorPage 404 HandlerMethod 호출!");
        printErrorInfo(request);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorpage500(HttpServletRequest request, HttpServletResponse response){
        log.info("ErrorPage 500 HandlerMethod 호출!");
        printErrorInfo(request);
        return "error-page/500";
    }

    /**
     * @RequsetMapping(produces = MediaType) : 요청 requset의 Accept타입에 따라 어느 핸들러 메서드 호출할지 동적으로 결정
     */
    @ResponseBody
    @RequestMapping(value = "error-page/500",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> errorPage500api(HttpServletRequest request, HttpServletResponse response){

        log.info("API ErrorPage 호출!");

        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("status",request.getAttribute(ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer statusCode = (Integer) request.getAttribute(ERROR_STATUS_CODE);

        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

    @ResponseBody
    @RequestMapping(value = "error-page/404",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> errorPage404api(HttpServletRequest request, HttpServletResponse response){
        log.info("API ErrorPage404 호출!");

        Map<String, Object> result =  new HashMap<>();
        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("ex",ex);
        result.put("message", request.getAttribute(ERROR_MESSAGE));
        result.put("status",request.getAttribute(ERROR_STATUS_CODE));

        return new ResponseEntity<>(result,HttpStatus.valueOf((Integer) request.getAttribute(ERROR_STATUS_CODE)));
    }

    private void printErrorInfo(HttpServletRequest request){
        log.info("ERROR_EXCEPTION = {} ",request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE = {} ",request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE = {} ",request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI = {} ",request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME = {} ",request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE = {} ",request.getAttribute(ERROR_STATUS_CODE));
        log.info("dipatcherType = {} ",request.getDispatcherType());
    }
}
