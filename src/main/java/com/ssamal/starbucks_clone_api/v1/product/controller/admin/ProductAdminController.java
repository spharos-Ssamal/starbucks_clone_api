package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/product")
public class ProductAdminController {

    private final ProductAdminService adminService;

    @PostMapping("/product/insert")
    public ResponseEntity<BaseRes<List<ProdAdminRes.AddProductRes>>> insertProduct(
        @RequestBody List<ProdAdminReq.AddProductReq> req) {
        List<ProdAdminRes.AddProductRes> result = adminService.addProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<BaseRes<ProdAdminRes.DeleteProductRes>> deleteProduct(
        @RequestBody ProdAdminReq.DeleteProduct req) {
        ProdAdminRes.DeleteProductRes result = adminService.deleteProduct(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
