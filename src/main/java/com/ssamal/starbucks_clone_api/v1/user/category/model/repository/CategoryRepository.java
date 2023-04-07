package com.ssamal.starbucks_clone_api.v1.user.category.model.repository;

import com.ssamal.starbucks_clone_api.v1.user.category.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByParentId(Long parentId);
}
