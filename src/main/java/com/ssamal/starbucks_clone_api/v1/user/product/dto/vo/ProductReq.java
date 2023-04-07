package com.ssamal.starbucks_clone_api.v1.user.product.dto.vo;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductReq {

    private ProductReq() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetPrePurchaseProductsInfoReq {

        private List<Long> productIds;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FilterParam {

        private Long mainCategory;
        private List<Long> subCategories;
        @Nullable
        private List<Long> seasonIds;
        @Nullable
        private List<Long> sizeIds;
        private Integer priceStart;
        private Integer priceEnd;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductsReq {

        private FilterParam filterParam;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProductsReq {

        private String productName;
        private FilterParam filterParam;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProductsByHashtagReq {

        private String hashtagName;
        private FilterParam filterParam;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetRecommendsReq {

        private List<Long> recommendIds;
    }
}
