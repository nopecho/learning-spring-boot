package nopecho.exception.advice;

import lombok.extern.slf4j.Slf4j;
import nopecho.exception.customexception.UserException;
import nopecho.exception.exhandler.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ControllerAdvice : 특정 컨트롤러에 @ExceptionHandler, @InitBinder 기능을 부여 해줌(대상 지정 안하면 모든 컨트롤러에 적용)
 * @RestControllerAdvice : @ControllerAdvice에 @ResponseBody 추가 된 것( 서버 API활용시 사용 )
 *
 * @RestControllerAdvice("해당 Advice적용할 패키지") : 패키지 하위 컨트롤러에 적용
 * @RestControllerAdvice(annotation = RestController.class) : 특정 애노테이션이 붙어있는 컨트롤러에 적용
 */
@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
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
}
