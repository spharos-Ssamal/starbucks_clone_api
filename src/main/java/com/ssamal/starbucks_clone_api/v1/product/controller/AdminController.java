package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/product/insert")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddProductRes>>> insertProduct(@RequestBody List<ProdAdminReq.AddProductReq> req) {
        List<ProdAdminRes.AddProductRes> result = adminService.addProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/category/new")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddMenuRes>>> addCategory(@RequestBody List<ProdAdminReq.AddCategory> req) {
        List<ProdAdminRes.AddMenuRes> result = adminService.addCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/category/addProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.AddProductToMenuRes>> addProductToCategory(@RequestBody ProdAdminReq.AddProductTo req) {
        ProdAdminRes.AddProductToMenuRes result = adminService.addProductToCategory(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/event/new")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddMenuRes>>> addEvent(@RequestBody List<ProdAdminReq.AddEvent> req) {
        List<ProdAdminRes.AddMenuRes> result = adminService.addEvent(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/event/addProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.AddProductToMenuRes>> addProductToEvent(@RequestBody ProdAdminReq.AddProductTo req) {
        ProdAdminRes.AddProductToMenuRes result = adminService.addProductToEvent(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/hashTag/new")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddMenuRes>>> newHashTag(@RequestBody List<ProdAdminReq.AddHashTag> req) {
        List<ProdAdminRes.AddMenuRes> result = adminService.addHashTag(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @PostMapping("/hashTag/addProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.AddProductToMenuRes>> addProductToHashTag(@RequestBody ProdAdminReq.AddProductTo req) {
        ProdAdminRes.AddProductToMenuRes result = adminService.addProductToHashTag(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.DeleteProductRes>> deleteProduct(@RequestBody ProdAdminReq.DeleteProduct req) {
        ProdAdminRes.DeleteProductRes result = adminService.deleteProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
