package nopecho.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        if(id.equals("ex")){
            throw  new RuntimeException("잘못된 사용자");
        }
        if(id.equals("404")){
            response.sendError(404,"404Errorrr!!");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못 된 입력");
        }
        return new MemberDto(id,"Nopecho");
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
