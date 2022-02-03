package nopecho.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ExceptionHandler 사용시 JSON으로 응답 할 객체 정의 (해당 객체에 예외 발생시 API 스펙을 정의 할 수 있다.)
 */
@Data
@AllArgsConstructor
public class ErrorResult {

    private String code;
    private String message;
}
