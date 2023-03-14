package com.ssamal.starbucks_clone_api.v1.product.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.product.enums.ProductStatus;
import com.ssamal.starbucks_clone_api.v1.product.enums.Season;
import com.ssamal.starbucks_clone_api.v1.product.enums.Size;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import java.util.List;
import lombok.*;

public class ProductDTO {

    private ProductDTO () {
        throw new IllegalStateException("DTO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DTO {

        private Long id;
        private String name;
        private Integer price;
        private String description;
        private String thumbnail;
        private ProductStatus status;
        private Size size;
        private Season season;

        public static DTO of(Product entity) {
            return ModelMapperUtils.getModelMapper().map(entity, DTO.class);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductInfo {
        private String name;
        private Integer price;
        private String description;
        private String thumbnail;
        private Size size;
        private Season season;
        public static ProductInfo of(Product entity) {
            return ModelMapperUtils.getModelMapper().map(entity, ProductInfo.class);
        }

        public static List<ProductInfo> of(List<Product> entities) {
            return entities.stream().map(ProductInfo::of).toList();
        }
    }

}
