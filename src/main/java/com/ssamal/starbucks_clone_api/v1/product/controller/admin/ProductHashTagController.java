package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductHashtagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[어드민] 해시태그 관리", description = "해시태그 데이터 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/hashtag")
public class ProductHashTagController {
    private final ProductHashtagService adminService;
    @Operation(summary = "해시태그 등록", description = "해시태그 등록 API 입니다.")
    @PostMapping("/event/new")
    public ResponseEntity<BaseRes<List<AddOptionRes>>> addHashtag(
        @RequestBody List<AddOption> req) {
        List<AddOptionRes> result = adminService.addHashTag(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 해시태그 등록", description = "상품 해시태그 등록 API 입니다.")
    @PostMapping("/hashTag/addProduct")
    public ResponseEntity<BaseRes<AddProductOptionRes>> addProductToHashTag(
        @RequestBody ProdAdminReq.AddProductTo req) {
        AddProductOptionRes result = adminService.addProductToHashTag(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
