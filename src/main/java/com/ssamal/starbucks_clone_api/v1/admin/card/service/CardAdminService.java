package com.ssamal.starbucks_clone_api.v1.admin.card.service;

import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.CardReq.CreateCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.CardReq.UpdateCardReq;
import com.ssamal.starbucks_clone_api.v1.service.coupon.dto.CardDTO;
import java.util.List;

public interface CardAdminService {

    Long createCard(CreateCardReq cardReq);

    List<CardDTO>cardList();

    Long updateCard(UpdateCardReq req);

    Long deleteCard(Long cardId);

}
