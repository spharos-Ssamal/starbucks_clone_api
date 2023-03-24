package com.ssamal.starbucks_clone_api.v1.coupon.controller.card;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.CardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "카드", description = "카드 요청 API 문서입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/v1")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "카드 생성", description = "카드 생성 API 입니다.")
    @PostMapping("/card/install")
    public ResponseEntity<BaseRes<Long>> cardInstall(@RequestBody CardReq cardReq){
        System.out.println(cardReq.getName());
        Long res = cardService.createCard(cardReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
    @Operation(summary = "카드 찾기", description = "카드 찾는 API 입니다.")
    @GetMapping("/card/getList")
    public List<CardDTO> cardDTOList(){
        return cardService.cardList();
    }
    @Operation(summary = "카드 수정", description = "카드 수정하는 API 입니다.")
    @PostMapping("/card/update")
    public Long cardUpdate(@RequestBody CardReq req){
        return cardService.updateCard(req);
    }

    @Operation(summary = "카드 삭제", description = "카드 삭제하는 API 입니다.")
    @PutMapping("/card/delete")
    public String deleteCard(@RequestBody CardReq req){
        return cardService.deleteCard(req);
    }


}
