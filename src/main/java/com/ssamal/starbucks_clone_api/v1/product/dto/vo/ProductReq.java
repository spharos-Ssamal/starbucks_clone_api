package com.ssamal.starbucks_clone_api.v1.product.dto.vo;

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
    public static class CreateProduct {
        private String name;
        private Integer price;
        private String description;
        private String thumbnail;
    }


    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GenerateCategory {
        private String name;
        private String type;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateProductCategory {
        private Long categoryId;
        private Long productId;
    }
}
