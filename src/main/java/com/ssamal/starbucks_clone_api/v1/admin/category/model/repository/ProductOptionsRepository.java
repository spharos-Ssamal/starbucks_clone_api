package com.ssamal.starbucks_clone_api.v1.admin.category.model.repository;

import com.ssamal.starbucks_clone_api.v1.admin.category.model.ProductOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductOptionsRepository extends JpaRepository<ProductOptions, Long>,
    JpaSpecificationExecutor<ProductOptions> {
    Page<ProductOptions> findAll(Specification<ProductOptions> spec, Pageable pageable);
}
