package com.ssamal.starbucks_clone_api.v1.cart.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemReq {
    private UUID userId;
    private Long productId;
    private int count;
}
