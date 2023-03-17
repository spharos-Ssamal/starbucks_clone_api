package com.ssamal.starbucks_clone_api.v1.coupon.controller.card;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.EGiftCardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.EGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.EGiftCardRes;
import com.ssamal.starbucks_clone_api.v1.coupon.service.EGiftCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "기프트 카드", description = "기프트 카드 요청 API 문서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/admin/v1")
public class EGiftCardController {

    private final EGiftCardService eGiftCardService;

    @Operation(summary = "기프트 카드 생성", description = "기프트 카드 생성 API 입니다.")
    @PostMapping("/egiftcard/insert")
    public ResponseEntity<BaseRes<Long>> InsertEGiftCard(@RequestBody EGiftCardReq egiftCard){
        Long res =eGiftCardService.createEGiftCard(egiftCard);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 조회", description = "기프트 카드 조회 API 입니다.")
    @GetMapping("/egiftcard/get")
    public ResponseEntity<BaseRes<List<EGiftCardDTO>>> GetEGiftCard(@RequestParam(name = "userId", defaultValue = "") UUID userId){
        List<EGiftCardDTO> res = eGiftCardService.eGiftCardList(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 업데이트", description = "기프트 카드 업데이트 API 입니다.")
    @PostMapping("/egiftcard/update")
    public ResponseEntity<BaseRes<EGiftCardRes>> updateEGiftCard(@RequestBody EGiftCardReq req){
        EGiftCardRes res = eGiftCardService.updateEGiftCard(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 삭제", description = "기프트 카드 삭제 API 입니다.")
    @PutMapping("/egiftcard/delete")
    public ResponseEntity<BaseRes<String>> deleteEGiftCard(@RequestParam(name = "egiftcardId", defaultValue = "") Long id){
        String answer = eGiftCardService.deleteEgiftCard(id);
        return ResponseEntity.ok().body(BaseRes.success(answer));
    }


}
