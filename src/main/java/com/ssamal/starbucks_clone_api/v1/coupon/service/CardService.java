package com.ssamal.starbucks_clone_api.v1.coupon.service;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.CardReq;
import java.util.List;

public interface CardService {

    Long createCard(CardReq cardReq);

    List<CardDTO>cardList();

    Long updateCard(CardReq req);

    String deleteCard(CardReq req);

}
