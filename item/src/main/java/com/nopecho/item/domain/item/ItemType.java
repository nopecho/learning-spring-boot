package com.nopecho.item.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ItemType { //상품 타입 enum (열거형 클래스)
    BOOK("도서"),FOOD("음식"),ETC("기타");

    private final String description;
}
