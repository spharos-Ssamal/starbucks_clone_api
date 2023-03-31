package com.ssamal.starbucks_clone_api.v1.admin.card.service;

import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.InsertEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.UpdateEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.service.coupon.dto.EGiftCardDTO;
import java.util.List;
import java.util.UUID;

public interface EGiftCardAdminService {

    Long createEGiftCard(InsertEGiftCardReq req);

    List<EGiftCardDTO> eGiftCardList(UUID userId);

    Long updateEGiftCard(UpdateEGiftCardReq req);

    Long deleteEgiftCard(Long id);
}
