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
import org.springframework.web.bind.annotation.*;

@Tag(name = "기프트 카드", description = "기프트 카드 요청 API 문서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1")
public class EGiftCardController {
    private final EGiftCardService eGiftCardService;

    @Operation(summary = "기프트 카드 생성", description = "기프트 카드 생성 API 입니다.")
    @PostMapping("/gift/install")
    public ResponseEntity<BaseRes<Long>> InsertEGiftCard(@RequestBody EGiftCardReq egiftCard){
        Long res = eGiftCardService.createEGiftCard(egiftCard);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 조회", description = "기프트 카드 조회 API 입니다.")
    @GetMapping("/gift/get")
    public List<EGiftCardRes> GetEGiftCard(
            @RequestParam(name = "userId") UUID userID){
        List<EGiftCardRes> res = eGiftCardService.eGiftCardList(userID);
        return res;
    }

    @Operation(summary = "기프트 카드 업데이트", description = "기프트 카드 업데이트 API 입니다.")
    @PutMapping("/gift/update")
    public ResponseEntity<BaseRes<Long>> updateEGiftCard(
            @RequestParam(name="giftid",defaultValue = "") Long id,
            @RequestParam(name = "amountpoint") int value,
            @RequestParam(name = "defaultcard") String defaultcard
                                                                 ){
        Long res = eGiftCardService.updateEGiftCard(id, value, defaultcard);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "기프트 카드 삭제", description = "기프트 카드 삭제 API 입니다.")
    @DeleteMapping("/gift/delete")
    public ResponseEntity<BaseRes<String>> deleteEGiftCard(@RequestParam(name = "egiftcardId", defaultValue = "") Long id){
        String answer = eGiftCardService.deleteEgiftCard(id);
        return ResponseEntity.ok().body(BaseRes.success(answer));
    }


}
