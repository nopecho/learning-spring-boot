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

    @NotNull(groups = UpdateCheck.class)
    private Long Id;

    @NotBlank(groups = {SaveCheck.class,UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class,UpdateCheck.class})
    @Range(min = 1000,max = 1000000 ,groups = {SaveCheck.class,UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class,UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
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

