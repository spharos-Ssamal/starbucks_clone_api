package com.ssamal.starbucks_clone_api.v1.cart.dto.vo;

import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import lombok.*;


public class CartRes {

    private CartRes() {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartItemRes {

        private Long id;
        private ProductDTO product;
        private int count;
    }

}
