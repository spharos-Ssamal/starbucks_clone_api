package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.service.ProductEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "[어드민] 기획전 관리", description = "기획전 데이터 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/event")
public class ProductEventController {

    private final ProductEventService adminService;

    @Operation(summary = "기획전 등록", description = "기획전 등록 API 입니다.")
    @PostMapping("/event/new")
    public ResponseEntity<BaseRes<List<AddOptionRes>>> addEvent(
        @RequestBody List<ProdAdminReq.AddOption> req) {
        List<AddOptionRes> result = adminService.addEvent(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "기획전 상품 등록", description = "기획전 상품 등록 API 입니다.")
    @PostMapping("/event/addProduct")
    public ResponseEntity<BaseRes<AddProductOptionRes>> addProductToEvent(
        @RequestBody ProdAdminReq.AddProductTo req) {
        AddProductOptionRes result = adminService.addProductToEvent(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }
}
