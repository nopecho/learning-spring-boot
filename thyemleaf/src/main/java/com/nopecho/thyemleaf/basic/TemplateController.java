package com.nopecho.thyemleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/fragment")
    public String template(){
        return "basic/template/fragment/fragmentMain";
    }

    @GetMapping("/layout")
    public String layout(){
        return "basic/template/layout/layoutMain";
    }

    @GetMapping("/layoutExtend")
    public String layoutExtend(){
        return "basic/template/layoutExtend/layoutExtendMain";
    }
}
