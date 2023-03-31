package com.ssamal.starbucks_clone_api.v1.options.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.options.dto.vo.SeasonRes.GetSeasonInfoRes;
import com.ssamal.starbucks_clone_api.v1.options.service.SeasonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "시즌", description = "시즌 정보 API 입니다.")
@RestController
@RequestMapping("/api/v1/season")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping("/info")
    public ResponseEntity<BaseRes<GetSeasonInfoRes>> getSeasonInfo() {
        GetSeasonInfoRes result = seasonService.getSeasonInfo();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
