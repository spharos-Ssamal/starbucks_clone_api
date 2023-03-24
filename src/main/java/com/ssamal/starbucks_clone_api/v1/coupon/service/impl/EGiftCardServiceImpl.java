package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.config.modelmapper.ModelMapperConfig;
import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.EGiftCardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.EGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.EGiftCardRes;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.EGiftCard;
import com.ssamal.starbucks_clone_api.v1.coupon.repository.CardRepository;
import com.ssamal.starbucks_clone_api.v1.coupon.repository.EGiftCardRepository;
import com.ssamal.starbucks_clone_api.v1.coupon.service.EGiftCardService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.model.repository.ServiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class EGiftCardServiceImpl implements EGiftCardService {

    private final ServiceUserRepository serviceUserRepository;
    private final CardRepository cardRepository;
    private final EGiftCardRepository eGiftCardRepository;

    @Override
    public Long createEGiftCard(EGiftCardReq req) {
        ServiceUser serviceUser = serviceUserRepository.findById(req.getUser_id())
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Card card = cardRepository.findById(req.getCard_id())
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));

        EGiftCard eGiftCard = EGiftCard.builder()
                .card(card)
                .serviceUser(serviceUser)
                .defaultCard(req.getDefaultCard())
                .amountPoint(req.getAmountPoint())
                .build();

        eGiftCardRepository.save(eGiftCard);
        return eGiftCard.getId();
    }

    @Override
    public List<EGiftCardRes> eGiftCardList(UUID userId) {
        List<EGiftCard> eGiftCardList = eGiftCardRepository.findByServiceUser(userId);
        return eGiftCardList.stream().map( t -> EGiftCardRes.builder()
            .id(t.getId())
            .amountPoint(t.getAmountPoint())
            .defaultCard(t.getDefaultCard())
            .build()).toList();
    }

    @Override
    public Long updateEGiftCard(Long id, int point, String defaultCard) {
        ModelMapperConfig config = new ModelMapperConfig();
        EGiftCard eGiftCard = eGiftCardRepository.findById(id)
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        EGiftCardDTO eGiftCardDTO = EGiftCardDTO.of(eGiftCard);
        eGiftCardDTO.setAmountPoint(point);
        eGiftCardDTO.setDefaultCard(defaultCard);
        eGiftCard = config.modelMapper().map(eGiftCardDTO, EGiftCard.class);
        eGiftCardRepository.save(eGiftCard);

        return eGiftCard.getId();
    }

    @Override
    public String deleteEgiftCard(Long id) {
        String answer;
        try{
            eGiftCardRepository.deleteById(id);
            answer = "삭제 성공";
        }catch (NotFoundException e){
            answer = "이미 삭제된 쿠폰입니다.";
        }
        return answer;
    }


}

