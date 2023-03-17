package com.ssamal.starbucks_clone_api.v1.coupon.controller.card;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.service.CardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/v1")
@RequiredArgsConstructor
public class CouponController {

    private final CardService cardService;

    @PostMapping("/coupon/install")
    public Long installCard(CardDTO cardDTO){
        Long res = cardService.createCard(cardDTO);
        return res;
    }

    @GetMapping("/coupon/getList")
    public List<CardDTO> getCardList(){
        List<CardDTO> cardDTOList = cardService.cardList();
        return cardDTOList;
    }
}
