package com.ssamal.starbucks_clone_api.v1.coupon.service;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.EGiftCardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.EGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.EGiftCardRes;
import java.util.List;
import java.util.UUID;

public interface EGiftCardService {

    Long createEGiftCard(EGiftCardReq req);

    List<EGiftCardDTO> eGiftCardList(UUID userId);

    EGiftCardRes updateEGiftCard(EGiftCardReq req);

    String deleteEgiftCard(Long id);
}
