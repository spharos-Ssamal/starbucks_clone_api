package com.ssamal.starbucks_clone_api.v1.admin.category.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddCategory;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddCategoryRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.options.model.Season;
import com.ssamal.starbucks_clone_api.v1.options.model.Size;
import com.ssamal.starbucks_clone_api.v1.options.model.repository.SeasonRespository;
import com.ssamal.starbucks_clone_api.v1.options.model.repository.SizeRepository;
import com.ssamal.starbucks_clone_api.v1.category.model.Category;
import com.ssamal.starbucks_clone_api.v1.category.model.repository.CategoryRepository;
import com.ssamal.starbucks_clone_api.v1.admin.category.service.OptionService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final CategoryRepository categoryRepository;
    private final SeasonRespository seasonRespository;
    private final SizeRepository sizeRepository;

    @Override
    public AddCategoryRes addCategories(AddCategory req) {

        if (req.getParentId() != 0) {
            Category parent = categoryRepository.findById(req.getParentId())
                .orElseThrow(() -> new CustomException(ResCode.CATEGORY_NOT_FOUND));

            Category newCategory = Category.builder()
                .name(req.getName())
                .isSizable(req.getIsSizable())
                .parent(parent)
                .build();

            categoryRepository.save(newCategory);
            return new AddCategoryRes(newCategory.getId());
        } else {
            Category newCategory = Category.builder()
                .name(req.getName())
                .build();

            categoryRepository.save(newCategory);
            return new AddCategoryRes(newCategory.getId());
        }
    }

    @Override
    public List<AddOptionRes> addSeason(List<AddOption> req) {
        List<AddOptionRes> response = new ArrayList<>();

        req.forEach(request -> {
            Season newSeason = Season.newData(request.getName());
            seasonRespository.save(newSeason);
            response.add(new AddOptionRes(newSeason.getId()));
        });

        return response;
    }

    @Override
    public List<AddOptionRes> addSizeMenu(List<AddOption> req) {
        List<AddOptionRes> response = new ArrayList<>();

        req.forEach(request -> {
            Size newSize = Size.newData(request.getName());
            sizeRepository.save(newSize);
            response.add(new AddOptionRes(newSize.getId()));
        });

        return response;
    }
}
