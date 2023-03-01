package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, Long> {
    Long deleteAllByProductId(Long productId);
}
