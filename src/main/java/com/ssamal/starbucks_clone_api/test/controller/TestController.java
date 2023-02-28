package com.ssamal.starbucks_clone_api.test.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<BaseRes<String>> helloTest() {
        return ResponseEntity.ok().body(BaseRes.success("Hello"));
    }
}
