package com.nopecho.item.web.validation;

import com.nopecho.item.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController // @RestController => 해당 애노테이션 클래스의 메서드들은 자동으로 @ResponseBody 애노테이션이 붙어있음
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){
        log.info("API 컨트롤러 호출");
        log.info("Binding된 객체 form = {}",form);
        if(bindingResult.hasErrors()){
            log.info("검증 오류 발생 errors = {}",bindingResult);
            return bindingResult.getFieldErrors();
        }

        log.info("성공 로직 실행");
        return form;
    }
}
