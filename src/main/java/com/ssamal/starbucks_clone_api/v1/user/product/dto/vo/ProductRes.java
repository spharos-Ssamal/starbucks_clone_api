package com.ssamal.starbucks_clone_api.v1.user.product.dto.vo;

import com.ssamal.starbucks_clone_api.v1.user.category.model.projection.CategoryAggregate;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping.ProductRecommend;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDetailImageDTO;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ProductRes {

    private ProductRes() {
        throw new IllegalStateException("Utility Class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductRes {

        private ProductDTO productInfo;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductDetailRes {

        private ProductDTO productInfo;
        private List<ProductDetailImageDTO> imageList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetPrePurchaseProductsInfoRes {
        private Long id;
        private String name;
        private Integer price;
        private String thumbnail;

        public static GetPrePurchaseProductsInfoRes of (Product entity) {
            return new GetPrePurchaseProductsInfoRes(entity.getId() ,entity.getName(), entity.getPrice(),
                entity.getThumbnail());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetProductCategoryAggregationRes {

        private Long categoryId;
        private String categoryName;
        private Integer count;

        public static GetProductCategoryAggregationRes of(CategoryAggregate result) {
            return new GetProductCategoryAggregationRes(result.getCategoryId(), result.getCategoryName(),
                result.getCount());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchProductRes {

        private List<ProductDTO> content;
        private Integer pageNo;
        private boolean isLast;
        private int totalPages;
        private Long totalElements;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RecommendProductRes {

        private String categoryName;
        private ProductDTO products;

        public static RecommendProductRes of(ProductRecommend productRecommend) {
            return new RecommendProductRes(productRecommend.getRecommend().getName(),
                ProductDTO.of(productRecommend.getProduct()));
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventProductsRes {

        private String detailImage;
        private List<EventProductRes> eventProductRes;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventProductRes {

        private String eventName;
        private ProductDTO products;

        public static EventProductRes of(ProductEvent productEvent) {
            return new EventProductRes(productEvent.getEvent().getName(),
                ProductDTO.of(productEvent.getProduct()));
        }
    }

}
