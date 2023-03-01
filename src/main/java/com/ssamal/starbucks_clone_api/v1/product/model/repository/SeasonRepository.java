package com.ssamal.starbucks_clone_api.v1.product.model.repository;

import com.ssamal.starbucks_clone_api.v1.product.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    boolean existsByName(String name);
}
