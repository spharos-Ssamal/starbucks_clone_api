package com.ssamal.starbucks_clone_api.v1.cart.dto.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


public class CartReq {

    private CartReq() {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class CartItemReq{
        private UUID userId;
        private Long productId;
        private int count;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateCartReq {
        private Long cartId;
        private int count;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RemoveCartItemReq {
        private List<Long> cartItemIds;
    }

}
