package com.ssamal.starbucks_clone_api.v1.user.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByIdIn(List<Long> productId);
    List<Product> findByIsBest(boolean isBest);
}
