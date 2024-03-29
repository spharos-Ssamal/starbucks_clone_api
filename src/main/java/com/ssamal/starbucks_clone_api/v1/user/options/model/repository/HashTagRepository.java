package com.ssamal.starbucks_clone_api.v1.user.options.model.repository;

import com.ssamal.starbucks_clone_api.v1.user.options.model.HashTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    boolean existsByName(String name);

    List<HashTag> findAllByNameStartingWith(String name);
}
