package nopecho.typeconverter.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Slf4j
@Controller
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model){
        Form form = new Form();
        form.setNumber(123456);
        form.setLocalDateTime(LocalDateTime.now());
        model.addAttribute("form",form);
        return "formatter-form";
    }

    @PostMapping("/formatter/edit")
    public String formatterEdit(@ModelAttribute Form form){
        return "formatter-view";
    }


    @Data
    static class Form{
        /**
         * @Format을 지원하는 애노테이션으로 객체의 필드를 타입을 특정 패턴으로 포매팅 가능
         */
        @NumberFormat(pattern = "###,###")
        private Integer number;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime localDateTime;
    }
}
