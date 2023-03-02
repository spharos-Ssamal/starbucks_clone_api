package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductHashTagRepository extends JpaRepository<ProductHashTag, Long> {
    Long deleteAllByProductId(Long productId);
    List<ProductHashTag> findAllByHashTagId(Long hashTagId);
}
