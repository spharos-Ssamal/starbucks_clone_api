package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

public class ProductReq {

    private ProductReq() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductsReq {

        private Long mainCategory;
        private List<Long> subCategories;
        @Nullable
        private List<Long> seasonIds;
        @Nullable
        private List<Long> sizeIds;
        @Nullable
        private Integer price;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProductsReq {

        private String productName;
        private Long mainCategory;
        private List<Long> subCategories;
        @Nullable
        private List<Long> seasonIds;
        @Nullable
        private List<Long> sizeIds;
        @Nullable
        private Integer price;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetRecommendsReq {

        private List<Long> recommendIds;
    }
}
