package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    /**
     * 모든 /hello-basic 요청에 해당 메소드 실행
     */
    @RequestMapping(value = "/hello-basic")
    public String hello() {
        log.info("hello");
        return "ok";
    }

    /**
     * RequestMapping에 method로 특정 method만 해당 컨트롤러 호출
     *
     * @RequestMapping 애노테이션에 method="매핑할 http메서드"가 있으면 해당 메서드의 요청만 받음
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGet() {
        log.info("mapping-get-v1");
        return "ok";
    }

    /**
     * 축양형 애노테이션
     *
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGet2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * !!중요!! PathVariable 사용
     * 변수명이 같으면 생략가능
     *
     * @PathVariable("userId") String userId -> @PathVariable userId
     * 보통 RestAPI 설계에 많이 사용
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath ={}", data);
        return "ok";
    }

    /**
     * PathVariable 다중으로 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * 기본 url + param = 에 있는 정보 까지 매핑해야 매핑됨( /mapping-param?mode=debug )
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappringParam");
        return "ok";
    }

    /**
     * 헤더 정보로 추가 매핑
     * 기본 url + 요청Http 헤더에 특정 헤더정보가 있어야 매핑됨
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반으로 추가 매핑
     * 클라이언트의 Http 요청 헤더의 특정 Content-Type에만 매핑됨
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return "ok";
    }

    /**
     * Accept 헤더 기반으로 매핑
     * Accept는 클라이언트가 응답 받을 수 있는 데이터 형식을 요청 헤더에 담아서 서버로 보냄
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduce(){
        log.info("mappingProduce");
        return "opk";
    }
}
