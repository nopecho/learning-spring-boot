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

    /**
     * @ExceptionHandler(Exception.class) : 해당 @RestController의 클래스에서 Exception.class의 예외가 발생하면 해당 메서드가 예외처리
     * @ResponseStatus : @ReponseBody,@RestCotroller에서 응답 상태코드를 지정 할 수 있음
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.info("@ExceptionHandler ex",e);
        return new ErrorResult("BAD",e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.info("@ExceptionHandler ex",e);
        ErrorResult userException = new ErrorResult("UserException", e.getMessage());
        return new ResponseEntity<>(userException,HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.info("@ExceptionHandler ex",e);
        return new ErrorResult("ex","내부 오류 입니다.");
    }

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
