package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProductReq {

    private ProductReq() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProducts {
        private Long categoryId;
        private Long eventId;
        private Long seasonId;
        private Long hashTagId;
        private Integer price;
        private String productName;

    }
}
