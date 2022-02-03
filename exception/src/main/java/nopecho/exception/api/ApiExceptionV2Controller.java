package nopecho.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import nopecho.exception.customexception.UserException;
import nopecho.exception.exhandler.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class ApiExceptionV2Controller {


    @GetMapping("api2/members/{id}")
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

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
