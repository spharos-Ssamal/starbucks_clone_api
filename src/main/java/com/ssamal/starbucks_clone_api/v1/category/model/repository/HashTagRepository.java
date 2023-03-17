package com.ssamal.starbucks_clone_api.v1.category.model.repository;

import com.ssamal.starbucks_clone_api.v1.category.model.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    boolean existsByName(String name);
    Optional<HashTag> findByName(String name);
}
