package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.config.modelmapper.ModelMapperConfig;
import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.CardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.repository.CardRepository;
import com.ssamal.starbucks_clone_api.v1.coupon.service.CardService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final ModelMapperConfig modelMapperConfig;

    @Override
    public Long createCard(CardReq cardReq) {
        Card card = modelMapperConfig.modelMapper().map(cardReq, Card.class);
        cardRepository.save(card);
        return card.getId();
    }

    @Override
    public List<CardDTO> cardList() {
        List<Card> cardList = cardRepository.findAll();
        return cardList.stream().map(t -> CardDTO.builder()
            .id(t.getId())
            .name(t.getName())
            .category(t.getCategory())
            .build()).toList();
    }

    @Override
    public Long updateCard(CardReq req) {
        Card card = cardRepository.findById(req.getId())
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        card.updateNameAndCategory(req.getName(),req.getCategory());
        cardRepository.save(card);
        return card.getId();
    }

    @Override
    public String deleteCard(CardReq req) {

        Card card = cardRepository.findById(req.getId())
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        cardRepository.deleteById(req.getId());
        return "삭제성공";
    }


}
