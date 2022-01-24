package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users") //공통 url을 클래스레벨에 설정
public class MappingClassController {

    /**
     * API 설계 예시 ( domain = 회원 )
     *
     * 회원 목록 조회 : GET /mapping/users
     * 회원 등록 : POST /mapping/users
     * 회원 조회 : GET /mapping/users/{userId}
     * 회원 수정 : PATCH /mapping/users/{userId}
     * 회원 삭제 : DELETE /mapping/users/{userId}
     */

    @GetMapping
    public String user(){
        return "get user";
    }

    @PostMapping
    public String addUser(){
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "find userId = "+userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId = " + userId;
    }
}
