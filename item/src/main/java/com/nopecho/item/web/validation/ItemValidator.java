package com.nopecho.item.web.validation;

import com.nopecho.item.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // instanceOf 와 유사 기능 (파라미터로 들어온 클래스 타입이 Item타입인지 검증해줌(자식까지))
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target; //Object타입이기 때문에 다운캐스팅해서 검증로직 실행
        log.info("objectName = {}",errors.getObjectName());

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName","required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price","range",new Object[]{1000,1000000},null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity","max",new Object[]{9999},null);
        }
        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10000) {
                errors.reject("totalPriceMin",new Object[]{10000,result},null);
            }
        }
    }
}
