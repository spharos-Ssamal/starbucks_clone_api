package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @GetMapping("/search")
    public ResponseEntity<BaseRes<String>> searchProducts() {
        return ResponseEntity.ok().body(BaseRes.success("Test"));
    }

}
