package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public Long createCard(CardDTO cardDTO) {
//        ServiceUser user = userRepository.findById(req.getUserId())
//            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Optional<Card> findCard = cardRepository.findByIdAndDeleted(cardDTO.getId(),false);

        Card card;
        if (findCard.isPresent()) {
          card = findCard.get();
          card.updateNameAndCategory(cardDTO.getName(),cardDTO.getCategory());
        }else{
            card = Card.builder()
                .name(cardDTO.getName())
                .category(cardDTO.getCategory())
                .build();
        }
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
        cardRepository.deleteById(req.getId());
        if(cardRepository.findById(req.getId()).isPresent()){
            return "삭제 실패";
        }
        return "삭제성공";
    }


}
