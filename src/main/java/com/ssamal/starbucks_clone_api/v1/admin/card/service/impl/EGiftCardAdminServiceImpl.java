package com.ssamal.starbucks_clone_api.v1.admin.card.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.InsertEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.UpdateEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.service.coupon.dto.EGiftCardDTO;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.Card;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.EGiftCard;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.repository.CardRepository;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.repository.EGiftCardRepository;
import com.ssamal.starbucks_clone_api.v1.admin.card.service.EGiftCardAdminService;
import java.util.List;
import java.util.UUID;

import com.ssamal.starbucks_clone_api.v1.service.user.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.service.user.model.repository.ServiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EGiftCardAdminServiceImpl implements EGiftCardAdminService {

    private final ServiceUserRepository serviceUserRepository;
    private final CardRepository cardRepository;
    private final EGiftCardRepository eGiftCardRepository;

    @Override
    public Long createEGiftCard(InsertEGiftCardReq req) {
        ServiceUser serviceUser = serviceUserRepository.findById(req.getUserId())
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Card card = cardRepository.findById(req.getCardId())
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));

        if (req.getIsDefault() && eGiftCardRepository.existsByServiceUserIdAndIsDefault(
            req.getUserId(), true)) {
            EGiftCard defaultCard = eGiftCardRepository.findByServiceUserIdAndIsDefault(
                    req.getUserId(), true)
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
            defaultCard.setIsDefault(false);
            eGiftCardRepository.save(defaultCard);
        }

        EGiftCard eGiftCard = EGiftCard.of(card, serviceUser, req);
        eGiftCardRepository.save(eGiftCard);
        return eGiftCard.getId();
    }

    @Override
    public List<EGiftCardDTO> eGiftCardList(UUID userId) {
        List<EGiftCard> eGiftCardList = eGiftCardRepository.findByServiceUserId(userId);
        return eGiftCardList.stream().map(EGiftCardDTO::of).toList();
    }

    @Override
    public Long updateEGiftCard(UpdateEGiftCardReq req) {

        EGiftCard eGiftCard = eGiftCardRepository.findById(req.getId())
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));

        if (req.getIsDefault() && eGiftCardRepository.existsByServiceUserIdAndIsDefault(
            req.getUserId(), true)) {
            EGiftCard defaultCard = eGiftCardRepository.findByServiceUserIdAndIsDefault(
                    req.getUserId(), true)
                .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
            defaultCard.setIsDefault(false);
            eGiftCardRepository.save(defaultCard);
        }

        eGiftCard.updateEgiftCard(req);
        eGiftCardRepository.save(eGiftCard);

        return eGiftCard.getId();
    }

    @Override
    public Long deleteEgiftCard(Long id) {
        EGiftCard eGiftCard = eGiftCardRepository.findById(id)
            .orElseThrow(() -> new CustomException(ResCode.CARD_NOT_FOUND));
        eGiftCard.deleteEGiftCard();
        eGiftCardRepository.save(eGiftCard);
        return eGiftCard.getId();
    }


}

