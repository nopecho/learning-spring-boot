package com.nopecho.item.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
//@ScriptAssert(lang = "javascript",script = "_this.price * _this.quantity >= 10000",message = "총합 10000원 넘게 입력하세요")
public class Item {

    private Long Id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000,max = 1000000)
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    private Boolean open; //판매여부
    private List<String> regions; //등록지역
    private ItemType itemType; //상품 종류
    private String deliveryCode; //배송 코드

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

