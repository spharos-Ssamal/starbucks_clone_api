package com.ssamal.starbucks_clone_api.v1.admin.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.admin.product.service.ProductAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "[어드민] 상품 관리", description = "상품 데이터 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/product")
public class ProductAdminController {

    private final ProductAdminService adminService;

    @Operation(summary = "상품 등록", description = "상품 등록 API 입니다.")
    @PostMapping("/insert")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddProductRes>>> insertProduct(
        @RequestBody List<ProdAdminReq.AddProductReq> req) {
        List<ProdAdminRes.AddProductRes> result = adminService.addProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 삭제", description = "상품 삭제 API 입니다.")
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.DeleteProductRes>> deleteProduct(
        @RequestBody ProdAdminReq.DeleteProduct req) {
        ProdAdminRes.DeleteProductRes result = adminService.deleteProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
