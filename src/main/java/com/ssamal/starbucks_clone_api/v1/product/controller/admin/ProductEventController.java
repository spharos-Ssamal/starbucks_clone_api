package com.ssamal.starbucks_clone_api.v1.product.controller.admin;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/event")
public class ProductEventController {
    private final ProductEventService adminService;

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
}
