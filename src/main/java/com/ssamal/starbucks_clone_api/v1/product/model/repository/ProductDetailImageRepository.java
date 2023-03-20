package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductDetailImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailImageRepository extends JpaRepository<ProductDetailImage, Long> {
    List<ProductDetailImage> findAllByProductIdOrderByIdAsc(Long productId);
}
