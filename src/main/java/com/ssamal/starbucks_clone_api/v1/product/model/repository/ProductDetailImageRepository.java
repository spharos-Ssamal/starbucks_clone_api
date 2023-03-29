package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductDetailImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductDetailImageRepository extends JpaRepository<ProductDetailImage, Long> {
    List<ProductDetailImage> findAllByProductIdOrderByIdAsc(Long productId);
    @Transactional
    Long deleteAllByProductId(Long productId);
}
