package com.ssamal.starbucks_clone_api.v1.service.category.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.service.category.dto.vo.CategoryRes.GetSubCategories;
import com.ssamal.starbucks_clone_api.v1.service.category.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카테고리", description = "카테고리 정보 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/subCategories")
    public ResponseEntity<BaseRes<GetSubCategories>> getSubCategories(@RequestParam(name = "categoryId", value = "") Long categoryId) {
        GetSubCategories result = categoryService.getSubCategories(categoryId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
