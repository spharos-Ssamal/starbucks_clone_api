package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.ProductCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<ProductCategory> {
    Long deleteAllByProductId(Long productId);
    List<ProductCategory> findAllByCategoryId(Long categoryId, Sort sort);
    List<ProductCategory> findAllByCategoryIdIn(List<Long> categoryId, Sort sort);

}
