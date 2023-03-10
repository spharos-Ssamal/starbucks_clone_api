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
    public static class SearchProductsReq {

        private Long mainCategory;
        private List<Long> subCategories;
        @Nullable
        private List<String> seasons;
        @Nullable
        private List<String> size;
        @Nullable
        private Integer price;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetRecommandsReq {

        private List<Long> recommendIds;
    }
}
