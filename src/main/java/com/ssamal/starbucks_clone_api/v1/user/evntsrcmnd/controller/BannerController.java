package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.category.dto.vo.BannerRes.BannerInfo;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "배너", description = "배너 정보 조회 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banner")
public class BannerController {

    private final BannerService bannerService;

    @Operation(summary = "배너 정보 조회 API", description = "현재 활성화 된 배너 데이터 조회 API 입니다.")
    @GetMapping
    public ResponseEntity<BaseRes<List<BannerInfo>>> getBannerInfo() {
        List<BannerInfo> result = bannerService.getBannerInfo();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
