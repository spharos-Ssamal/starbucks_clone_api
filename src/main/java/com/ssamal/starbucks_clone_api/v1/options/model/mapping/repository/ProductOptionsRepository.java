package com.ssamal.starbucks_clone_api.v1.options.model.mapping.repository;

import com.ssamal.starbucks_clone_api.v1.options.model.mapping.ProductOptions;
import com.ssamal.starbucks_clone_api.v1.category.model.projection.CategoryAggregate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductOptionsRepository extends JpaRepository<ProductOptions, Long>,
    JpaSpecificationExecutor<ProductOptions> {
    Page<ProductOptions> findAll(Specification<ProductOptions> spec, Pageable pageable);
    Boolean existsByCategoryIdAndProductId(Long categoryId, Long productId);
    Long deleteAllByProductId(Long productId);
    @Query(value = "SELECT s.category.id as categoryId, s.category.name as categoryName, COUNT(s.id) as count FROM ProductOptions s WHERE s.product.id in :productId AND s.category.id in :categoryId GROUP BY s.category.id ORDER BY s.category.id ")
    List<CategoryAggregate> categoryAggregate(List<Long> productId, List<Long> categoryId);
}
