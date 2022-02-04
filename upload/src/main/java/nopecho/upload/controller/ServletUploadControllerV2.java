package nopecho.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String savePath;

    @GetMapping("/upload")
    public String uploadForm(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV2(HttpServletRequest request) throws ServletException, IOException {
        log.info("request = {}",request);

        String itemName = request.getParameter("itemName");
        log.info("ItemName = {}",itemName);

        //mulitpart request의 각각의 파트를 컬렉션으로 받아옴
        Collection<Part> parts = request.getParts();
        log.info("parts = {}",parts);

        for (Part part : parts) {
            log.info("===PART===");
            log.info("part name = {}",part.getName());
            Collection<String> headerNames = part.getHeaderNames(); //각각의 part도 header와 body를 가짐
            for (String headerName : headerNames) {
                log.info("header = {}: {}",headerName,part.getHeader(headerName));
            }
            //multipart의 file part의 파일명 뽑는 편의 메서드
            log.info("submittedFileName = {}",part.getSubmittedFileName());
            log.info("size = {}",part.getSize()); //part body size

            //데이터 읽기
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body = {}",body);

            if(part.getSubmittedFileName() != null){
                String path = savePath + part.getSubmittedFileName();
                log.info("파일 저장 path = {}",path);
                part.write(path);
            }
        }

        return "upload-form";
    }
}
