package com.ssamal.starbucks_clone_api.v1.product.dto.vo.product;

import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.ProductRecommend;
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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecommendProductRes {
        private String categoryName;
        private ProductInfo products;

        public static RecommendProductRes fromEntity(ProductRecommend productRecommend){
            return new RecommendProductRes(productRecommend.getRecommend().getName(),
                    ProductInfo.fromEntity(productRecommend.getProduct()));
        }
    }

}
