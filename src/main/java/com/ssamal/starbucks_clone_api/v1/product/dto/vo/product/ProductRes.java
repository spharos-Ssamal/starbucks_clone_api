package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductRes {
    private ProductRes () {
        throw new IllegalStateException("Utility Class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductRes{
        private ProductInfo productInfo;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProductRes {
        private List<ProductInfo> content;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;
    }

}
