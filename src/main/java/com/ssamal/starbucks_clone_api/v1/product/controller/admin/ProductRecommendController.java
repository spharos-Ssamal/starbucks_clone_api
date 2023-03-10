package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/recommend")
public class ProductRecommendController {

    private final ProductRecommendService adminService;

    @PostMapping("/recommend/new")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddMenuRes>>> addRecommend(
        @RequestBody List<ProdAdminReq.AddRecommend> req) {
        List<ProdAdminRes.AddMenuRes> result = adminService.addRecommend(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/recommend/addProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.AddProductToMenuRes>> addProductToRecommend(
        @RequestBody ProdAdminReq.AddProductTo req) {
        ProdAdminRes.AddProductToMenuRes result = adminService.addProductToRecommend(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
