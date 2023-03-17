package com.ssamal.starbucks_clone_api.v1.admin.category.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddCategory;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddCategoryRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.service.OptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[어드민]상품 옵션", description = "상품 옵션 등록 및 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/option")
public class OptionController {

    private final OptionService optionService;

    @Operation(summary = "카테고리 등록", description = "카테고리 등록 API 입니다.")
    @PostMapping("/category/new")
    public ResponseEntity<BaseRes<AddCategoryRes>> addCategory(
        @RequestBody AddCategory req) {
        AddCategoryRes result = optionService.addCategories(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "시즌 등록", description = "시즌 등록 API 입니다.")
    @PostMapping("/season/new")
    public ResponseEntity<BaseRes<List<AddOptionRes>>> addCategory(
        @RequestBody List<AddOption> req) {
        List<AddOptionRes> result = optionService.addSeason(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "사이즈 등록", description = "상품 사이즈 등록 API 입니다.")
    @PostMapping("/size/new")
    public ResponseEntity<BaseRes<List<AddOptionRes>>> addSize(
        @RequestBody List<AddOption> req) {
        List<AddOptionRes> result = optionService.addSizeMenu(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
