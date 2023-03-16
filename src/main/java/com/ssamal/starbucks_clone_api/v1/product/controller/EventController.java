package com.ssamal.starbucks_clone_api.v1.product.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.EventRes.ActivatedEventsRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes.EventProductRes;
import com.ssamal.starbucks_clone_api.v1.product.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "이벤트", description = "이벤트 및 관련 상품 조회 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "현재 활성화 된 이벤트 항목 조회", description = "현재 활성화 된 이벤트 항목 조회 API 입니다.")
    @GetMapping("/active")
    public ResponseEntity<BaseRes<List<ActivatedEventsRes>>> getEventsByStatus() {
        List<ActivatedEventsRes> result = eventService.getActivatedEvents();
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "이벤트 항목 단건 조회", description = "이벤트 항목 단건 조회 API 입니다.")
    @GetMapping("/get")
    public ResponseEntity<BaseRes<List<EventProductRes>>> getProductsByEvent(
        @RequestParam(name = "eventId", defaultValue = "") Long eventId) {
        List<EventProductRes> result = eventService.getProductsByEvent(eventId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }


}
