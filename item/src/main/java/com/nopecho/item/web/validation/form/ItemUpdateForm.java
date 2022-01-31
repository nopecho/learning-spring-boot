package com.nopecho.item.web.validation.form;

import com.nopecho.item.domain.item.ItemType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.spi.LocaleNameProvider;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000,max = 1000000)
    private Integer price;

    //수정시에는 제약 없음
    private Integer quantity;

    private Boolean open; //판매여부
    private List<String> regions; //등록지역
    private ItemType itemType; //상품 종류
    private String deliveryCode; //배송 코드
}
