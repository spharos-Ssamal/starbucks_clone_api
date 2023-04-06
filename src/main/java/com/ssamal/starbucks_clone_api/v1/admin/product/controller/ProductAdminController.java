package com.ssamal.starbucks_clone_api.v1.admin.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq.AddImageReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.admin.product.service.ProductAdminService;
import com.ssamal.starbucks_clone_api.v1.user.product.dto.ProductDTO;
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

    @Operation(summary = "상품 상세 이미지 등록", description = "상품 상세 이미지 등록 API 입니다.")
    @PostMapping("/insert/image")
    public ResponseEntity<BaseRes<List<Long>>> insertProductImages(
        @RequestBody AddImageReq req) {
        List<Long> result = adminService.addProductDetailImages(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 업데이트", description = "싱픔 업데이트 API 입니다.")
    @PutMapping("/update")
    public ResponseEntity<BaseRes<List<Long>>> updateProduct(
            @RequestBody ProdAdminReq.UpdateProductInfo req){
        List<Long> result = adminService.updateProductAndProductDetailImages(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "상품 삭제", description = "상품 삭제 API 입니다.")
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.DeleteProductRes>> deleteProduct(
        @RequestBody ProdAdminReq.DeleteProduct req) {
        ProdAdminRes.DeleteProductRes result = adminService.deleteProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "베스트 상품", description = "베스트 상품 등록 API 입니다")
    @PutMapping("/bestProduct")
    public ResponseEntity<BaseRes<List<ProdAdminReq.BestProduct>>> bestProduct(
            @RequestParam Long categoryId, @RequestParam int rank
    ) {
        List<ProdAdminReq.BestProduct> bestProductList = adminService.updateBestProduct(categoryId, rank);
        return ResponseEntity.ok().body(BaseRes.success(bestProductList));
    }

}
