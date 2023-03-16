package com.ssamal.starbucks_clone_api.v1.product.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddCategory;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.model.Category;
import com.ssamal.starbucks_clone_api.v1.product.model.Season;
import com.ssamal.starbucks_clone_api.v1.product.model.Size;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.CategoryRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.SeasonRespository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.SizeRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.OptionService;
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
    public AddOptionRes addCategories(AddCategory res) {

        if (res.getParentId() != 0) {
            Category parent = categoryRepository.findById(res.getParentId())
                .orElseThrow(() -> new CustomException(ResCode.CATEGORY_NOT_FOUND));

            Category newCategory = Category.builder()
                .name(res.getName())
                .parent(parent)
                .build();

            categoryRepository.save(newCategory);
            return new AddOptionRes(newCategory.getId());
        } else {
            Category newCategory = Category.builder()
                .name(res.getName())
                .build();

            categoryRepository.save(newCategory);
            return new AddOptionRes(newCategory.getId());
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
