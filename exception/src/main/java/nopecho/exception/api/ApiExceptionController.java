package nopecho.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nopecho.exception.customexception.BadRequsetException;
import nopecho.exception.customexception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("404")) {
            response.sendError(404, "404Errorrr!!");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못 된 입력");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류!");
        }
        return new MemberDto(id, "Nopecho");
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatus() {
        throw new BadRequsetException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatus2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"error.bad",new IllegalArgumentException());
    }

    @GetMapping("/api/defualt-handler-ex")
    public String responseDefualt(@RequestParam Integer data){
        return "ok";
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}


