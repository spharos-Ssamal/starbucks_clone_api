package com.ssamal.starbucks_clone_api.v1.options.model.mapping.repository.specification;

import com.ssamal.starbucks_clone_api.v1.options.model.mapping.ProductOptions;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class ProductOptionSpecification {

    private ProductOptionSpecification() {
        throw new IllegalStateException("JPA Specification class");
    }

    public static Specification<ProductOptions> likeProductName(String productName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
            root.get("product").get("name"), "%" + productName + "%");
    }

    public static Specification<ProductOptions> inProductId(List<Long> productId) {
        return (root, query, criteriaBuilder) -> root.get("product").get("id").in(productId);
    }

    public static Specification<ProductOptions> inCategoryId(List<Long> categoryId) {
        return (root, query, criteriaBuilder) -> root.get("category").get("id").in(categoryId);
    }

    public static Specification<ProductOptions> inSeasonId(List<Long> seasonId) {
        return (root, query, criteriaBuilder) -> root.get("season").get("id").in(seasonId);
    }

    public static Specification<ProductOptions> inSizeId(List<Long> sizeId) {
        return (root, query, criteriaBuilder) -> root.get("size").get("id").in(sizeId);
    }

    public static Specification<ProductOptions> lessThanPrice(Integer price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(
            root.get("product").get("price"), price);
    }

}
