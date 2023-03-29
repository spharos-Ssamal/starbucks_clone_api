package com.ssamal.starbucks_clone_api.v1.category.service;

import com.ssamal.starbucks_clone_api.v1.category.dto.vo.CategoryRes.GetSubCategories;

public interface CategoryService {
    GetSubCategories getSubCategories(Long categoryId);

}
