package com.ssamal.starbucks_clone_api.v1.admin.card.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.CardReq.CreateCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.CardReq.UpdateCardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.model.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.model.repository.CardRepository;
import com.ssamal.starbucks_clone_api.v1.admin.card.service.CardAdminService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CardAdminServiceImpl implements CardAdminService {

    private final CardRepository cardRepository;

    @Override
    public Long createCard(CreateCardReq req) {
        Card card = Card.builder()
            .name(req.getName())
            .build();
        cardRepository.save(card);
        return card.getId();
    }

    @Override
    public List<CardDTO> cardList() {
        List<Card> cardList = cardRepository.findAll();
        return cardList.stream().map(CardDTO::of).toList();
    }

    @Override
    public Long updateCard(UpdateCardReq req) {
        Card card = cardRepository.findById(req.getCardId())
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        card.updateName(req.getName());
        cardRepository.save(card);
        return card.getId();
    }

    @Override
    public Long deleteCard(Long cardId) {

        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        card.deleteCard();
        cardRepository.save(card);
        return card.getId();
    }


}
