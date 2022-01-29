package com.nopecho.thyemleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String text(Model model){
        model.addAttribute("data","hello! thymeleaf");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String unescaped(Model model){
        model.addAttribute("data","<b>Hello! ThymeLeaf</b>");
        return "basic/text-unescaped";
    }
}
