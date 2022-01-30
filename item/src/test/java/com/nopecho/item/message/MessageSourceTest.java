package com.nopecho.item.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void 메시지_소스_테스트(){
        String hello = ms.getMessage("hello", null, null);
        assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void 찾는_메시지_없을때_테스트(){
        assertThatThrownBy(() -> ms.getMessage("no_code",null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void 기본_메시지_설정_테스트(){
        String message = ms.getMessage("no_codde", null, "기본 메시지", null);
        assertThat(message).isEqualTo("기본 메시지");
    }

    @Test
    void 메시지_인자_테스트(){
        String result = ms.getMessage("hello.name", new Object[]{"테스트"}, null);
        assertThat(result).isEqualTo("안녕 테스트");
    }

    @Test
    void 기본_언어_테스트(){
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void 영어_테스트(){
        assertThat(ms.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }

    @Test
    void 영어_인자_테스트(){
        assertThat(ms.getMessage("hello.name",new Object[]{"nopecho"},Locale.ENGLISH)).isEqualTo("hello nopecho");
    }
}
