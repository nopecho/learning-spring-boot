package com.nopecho.item.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * code = displayName
 *
 * FAST = 빠른배송
 * NOMAL = 보통배송
 * SLOW = 느린배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName;
}
