package com.zerorock.ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //별도의 화면 없이 데이터를 전송
public class SampleController {
    @GetMapping("/hello") //http://~ 뒤에 /hello인 주소를 찾아서 본문 코드 실행
    public String[] hello(){
        return new String[]{"hello","world"};
    }
}
