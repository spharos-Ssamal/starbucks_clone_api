package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

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
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ServiceUserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        Optional<EGiftCard>checkEGiftCard = eGiftCardRepository.findById(req.getCard_id());
        EGiftCard eGiftCard;
        if(checkEGiftCard.isPresent()){
            eGiftCard = checkEGiftCard.get();
        }else{
            eGiftCard=EGiftCard.builder()
                .card(card)
                .serviceUser(serviceUser)
                .defaultCard(req.getDefaultCard())
                .balance(req.getBalance())
                .amountPoint(req.getAmountPoint())
                .build();
        }
        eGiftCardRepository.save(eGiftCard);
        return eGiftCard.getId();
    }

    @Override
    public List<EGiftCardDTO> eGiftCardList(UUID userId) {
        List<EGiftCard> eGiftCardList = eGiftCardRepository.findByUserId(userId);
        return eGiftCardList.stream().map( t -> EGiftCardDTO.builder()
            .id(t.getId())
            .amountPoint(t.getAmountPoint())
            .balance(t.getBalance())
            .defaultCard(t.getDefaultCard())
            .build()).toList();
    }

    @Override
    public EGiftCardRes updateEGiftCard(EGiftCardReq req) {
        EGiftCard eGiftCard = eGiftCardRepository.findById(req.getId())
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        eGiftCard.builder()
            .balance(req.getBalance())
            .amountPoint(req.getAmountPoint())
            .defaultCard(req.getDefaultCard())
            .build();
        eGiftCardRepository.save(eGiftCard);
        EGiftCardRes eGiftCardRes = ModelMapperUtils.getModelMapper().map(eGiftCard,EGiftCardRes.class);
        return eGiftCardRes;
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

