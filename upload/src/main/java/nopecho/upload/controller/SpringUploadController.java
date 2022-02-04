package nopecho.upload.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String path;

    @GetMapping("/upload")
    public String uploadForm(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String itemName,
                         @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        log.info("requset = {}",request);
        log.info("itemName = {}",itemName);
        log.info("file = {}",file);

        if(!file.isEmpty()){
            String fullPath = path+file.getOriginalFilename(); //OriginalFileName은 파일의 실제 파일명 반환
            log.info("저장 경로  = {}",fullPath);
            file.transferTo(new File(fullPath));
        }
        return "upload-form";
    }
}
