package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductHashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/hashtag")
public class ProductHashTagController {
    private final ProductHashTagService adminService;
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
}
