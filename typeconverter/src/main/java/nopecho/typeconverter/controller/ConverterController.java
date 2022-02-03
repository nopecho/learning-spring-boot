package nopecho.typeconverter.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nopecho.typeconverter.type.IpPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String convertView(Model model){
        model.addAttribute("number",10000);
        model.addAttribute("ipPort",new IpPort("localhost",8080));
        return "convert-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model){
        IpPort ipPort = new IpPort("localhost",8080);
        IpPortForm form = new IpPortForm(ipPort);
        model.addAttribute("form",form);
        return "converter-form";
    }

    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute IpPortForm form, Model model){
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort",ipPort);
        return "convert-view";
    }

    @Data
    @AllArgsConstructor
    static class IpPortForm{
        private IpPort ipPort;
    }
}
