package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import jakarta.annotation.Nullable;
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
    public static class SearchProductsReq {
        @Nullable
        private Long categoryId;
        @Nullable
        private Long seasonId;
        @Nullable
        private Integer price;

    }
}
