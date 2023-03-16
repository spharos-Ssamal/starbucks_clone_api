package com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.mapping.ProductOptions;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionsRepository extends JpaRepository<ProductOptions, Long> {
    List<ProductOptions> findAllByCategoryId(Long categoryId, Sort sort);
    List<ProductOptions> findAllByCategoryIdIn(List<Long> categoryIds, Sort sort);
}
