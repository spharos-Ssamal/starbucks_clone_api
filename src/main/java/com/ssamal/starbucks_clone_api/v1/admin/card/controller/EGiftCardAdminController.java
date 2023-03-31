package com.ssamal.starbucks_clone_api.v1.admin.card.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.InsertEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.UpdateEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.card.service.EGiftCardAdminService;
import com.ssamal.starbucks_clone_api.v1.user.coupon.dto.EGiftCardDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[어드민] 기프트 카드", description = "기프트 카드 어드민 API 문서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/gift")
public class EGiftCardAdminController {

    private final EGiftCardAdminService eGiftCardService;

    @Operation(summary = "기프트 카드 생성", description = "기프트 카드 생성 API 입니다.")
    @PostMapping("/create")
    public ResponseEntity<BaseRes<Long>> insertEGiftCard(
        @RequestBody InsertEGiftCardReq req) {
        Long res = eGiftCardService.createEGiftCard(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 조회", description = "기프트 카드 조회 API 입니다.")
    @GetMapping("/get")
    public ResponseEntity<BaseRes<List<EGiftCardDTO>>> getEGiftCard(
        @RequestParam(name = "userId") UUID userID) {
        List<EGiftCardDTO> res = eGiftCardService.eGiftCardList(userID);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 업데이트", description = "기프트 카드 업데이트 API 입니다.")
    @PutMapping("/update")
    public ResponseEntity<BaseRes<Long>> updateEGiftCard(
        @RequestBody UpdateEGiftCardReq req) {
        Long res = eGiftCardService.updateEGiftCard(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 삭제", description = "기프트 카드 삭제 API 입니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<BaseRes<Long>> deleteEGiftCard(
        @RequestParam(name = "eGiftCardId", defaultValue = "") Long id) {
        Long answer = eGiftCardService.deleteEgiftCard(id);
        return ResponseEntity.ok().body(BaseRes.success(answer));
    }


}
