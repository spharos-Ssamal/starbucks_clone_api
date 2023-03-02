package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

public class ProductRes {
    private ProductRes () {
        throw new IllegalStateException("Utility Class");
    }
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProductsRes {
        private List<ProductInfo> results;
    }

}
