package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSeasonRepository extends JpaRepository<ProductSeason, Long> {
    Long deleteAllByProductId(Long productId);
}
