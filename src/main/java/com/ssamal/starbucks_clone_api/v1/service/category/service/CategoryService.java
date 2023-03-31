package com.ssamal.starbucks_clone_api.v1.service.category.service;

import com.ssamal.starbucks_clone_api.v1.service.category.dto.vo.CategoryRes.GetSubCategories;

public interface CategoryService {
    GetSubCategories getSubCategories(Long categoryId);

}
