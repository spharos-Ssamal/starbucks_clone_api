package com.ssamal.starbucks_clone_api.v1.user.category.service;

import com.ssamal.starbucks_clone_api.v1.user.category.dto.vo.CategoryRes.GetSubCategories;

public interface CategoryService {
    GetSubCategories getSubCategories(Long categoryId);

}
