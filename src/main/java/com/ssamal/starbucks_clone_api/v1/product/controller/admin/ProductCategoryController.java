package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/category")
public class ProductCategoryController {

    private final ProductCategoryService adminService;

    @PostMapping("/category/new")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddMenuRes>>> addCategory(
        @RequestBody List<ProdAdminReq.AddCategory> req) {
        List<ProdAdminRes.AddMenuRes> result = adminService.addCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/category/addProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.AddProductToMenuRes>> addProductToCategory(
        @RequestBody ProdAdminReq.AddProductTo req) {
        ProdAdminRes.AddProductToMenuRes result = adminService.addProductToCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
