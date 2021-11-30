package com.zerorock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.zerorock.ex2.entity.Memo;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests { //테스트 클래스

    @Autowired
    MemoRepository memoRepository;

//    @Test
//    public void testClass(){
//        System.out.println(memoRepository.getClass().getName());
//    }
//    @Test
//    public void testInsertDummies(){ //DB에 엔티티객체 만들고 삽입
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            Memo memo = Memo.builder().memoText("SAMPLE...."+i).build();
//            memoRepository.save(memo);
//        });
//    }
//    @Transactional //트랜젝션 처리 어노테이션
//    @Test
//    public void testSelect(){ //DB에서 컬럼이 100인 조회
//        Long mno = 100L;
//
//       Memo memo = memoRepository.getOne(mno);
//
//        System.out.println("=========================================");
//
//        System.out.println( memo);
//    }
//
//    @Test
//    public void testUpdate(){
//        Memo memo = Memo.builder().mno(100L).memoText("UPDATE TEXT!!").build();
//
//        System.out.println(memoRepository.save(memo));
//    }
//
//    @Test
//    public void testDelete(){
//        Long mno = 100L;
//        memoRepository.deleteById(mno);
//    }

    @Test
    public void testInsertDummies(){
        Memo memo = Memo.builder().mno(1L).memoText2("이렇게 작성하면 되는건가?").build();
        System.out.println(memoRepository.save(memo));

    }
}
