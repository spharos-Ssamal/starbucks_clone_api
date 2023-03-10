package com.ssamal.starbucks_clone_api.v1.product.dto;

import lombok.*;


public class CategoryDTO {

    private CategoryDTO() {
        throw new IllegalStateException("DTO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DTO {

        private Long id;
        private String name;
        private String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Info {

        private String name;
        private String type;
    }

}
