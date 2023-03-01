package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    boolean existsByName(String name);
    Optional<HashTag> findByName(String name);
}
