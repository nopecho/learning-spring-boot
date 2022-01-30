package com.nopecho.item.validation;

import com.nopecho.item.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.*;

public class MessageCodesResolverTest {

    MessageCodesResolver cr = new DefaultMessageCodesResolver();

    @Test
    void 메시지_코드_리졸버_객체(){
        String[] messageCodes = cr.resolveMessageCodes("required", "item","name",null);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly("required.item.name","required.name","required");
    }

    @Test
    void 메시지_코드_리졸버_필드(){
        String[] messageCodes = cr.resolveMessageCodes("required", "item", "price", Integer.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly(
                "required.item.price",
                "required.price",
                "required.java.lang.Integer",
                "required"
        );
    }
}
