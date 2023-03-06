package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductRecommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRecommandRepository extends JpaRepository<ProductRecommand, Long> {
    Long deleteAllByProductId(Long productId);
    List<ProductRecommand> findAllByRecommandId(Long recommandId);
}
