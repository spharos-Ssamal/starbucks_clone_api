package com.ssamal.starbucks_clone_api.v1.category.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.category.model.repository.SizeRepository;
import com.ssamal.starbucks_clone_api.v1.category.dto.vo.CategoryRes.GetSubCategories;
import com.ssamal.starbucks_clone_api.v1.category.dto.vo.CategoryRes.SizeInfo;
import com.ssamal.starbucks_clone_api.v1.category.dto.vo.CategoryRes.SubCategoryInfo;
import com.ssamal.starbucks_clone_api.v1.category.model.Category;
import com.ssamal.starbucks_clone_api.v1.category.model.repository.CategoryRepository;
import com.ssamal.starbucks_clone_api.v1.category.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final SizeRepository sizeRepository;

    @Override
    public GetSubCategories getSubCategories(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new CustomException(ResCode.CATEGORY_NOT_FOUND));

        List<SizeInfo> sizeInfos = new ArrayList<>();

        List<SubCategoryInfo> subCategoryInfos = categoryRepository.findAllByParentId(categoryId)
            .stream().map(SubCategoryInfo::of).toList();

        if (category.isSizable()) {
            sizeInfos = sizeRepository.findAll()
                .stream().map(SizeInfo::of).toList();
        }

        return new GetSubCategories(subCategoryInfos, sizeInfos);
    }
}
