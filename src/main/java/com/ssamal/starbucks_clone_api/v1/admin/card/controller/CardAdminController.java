package com.ssamal.starbucks_clone_api.v1.admin.card.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.CardReq.CreateCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.CardReq.UpdateCardReq;
import com.ssamal.starbucks_clone_api.v1.service.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.admin.card.service.CardAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[어드민] 카드", description = "카드 어드민 API 문서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/card")
public class CardAdminController {

    private final CardAdminService cardAdminService;

    @Operation(summary = "카드 생성", description = "카드 생성 API 입니다.")
    @PostMapping("/create")
    public ResponseEntity<BaseRes<Long>> cardInstall(@RequestBody CreateCardReq cardReq){
        Long res = cardAdminService.createCard(cardReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
    @Operation(summary = "카드 찾기", description = "카드 찾는 API 입니다.")
    @GetMapping("/getList")
    public ResponseEntity<BaseRes<List<CardDTO>>> cardDTOList(){
        return ResponseEntity.ok().body(BaseRes.success(cardAdminService.cardList()));
    }
    @Operation(summary = "카드 수정", description = "카드 수정하는 API 입니다.")
    @PostMapping("/update")
    public ResponseEntity<BaseRes<Long>> cardUpdate(@RequestBody UpdateCardReq req){
        return ResponseEntity.ok().body(BaseRes.success(cardAdminService.updateCard(req)));
    }

    @Operation(summary = "카드 삭제", description = "카드 삭제하는 API 입니다.")
    @PutMapping("/delete")
    public ResponseEntity<BaseRes<Long>> deleteCard(@RequestParam Long cardId){
        return ResponseEntity.ok().body(BaseRes.success(cardAdminService.deleteCard(cardId)));
    }


}
