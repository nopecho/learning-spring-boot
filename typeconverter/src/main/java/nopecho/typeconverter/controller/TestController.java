package nopecho.typeconverter.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nopecho.typeconverter.type.IpPort;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;

@Slf4j
@RestController
public class TestController {

//    @ExceptionHandler
    public String exceptionTest(TypeMismatchException e){
        log.info("exception",e);
        return "Exception!";
    }

    @GetMapping("/hello-v1")
    public String testV1(HttpServletRequest request){
        String data = request.getParameter("data");
        Integer intValue = Integer.parseInt(data);
        log.info("RequestParameter Interger = {}",intValue);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String testV2(@RequestParam Integer data){
        log.info("@RequestParam => String to Integer 자동 형변환 = {}",data);
        return String.valueOf(data);
    }

    @GetMapping("/hello-v3")
    public UserDto testV3(@Validated @ModelAttribute UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("bindingResult.getObjectName = {}",bindingResult.getObjectName());
            log.info("bindingResult.FieldError  = {}",bindingResult.getFieldErrors("id"));
            return null;
        }
        log.info("userDto = {}",userDto);
        return userDto;
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort data){
        log.info("ip = {}",data.getIp());
        log.info("port = {}",data.getPort());
        return data.toString();
    }

    @Data
    @AllArgsConstructor
    static class UserDto{

        @Max(10)
        private Long id;
    }
}
