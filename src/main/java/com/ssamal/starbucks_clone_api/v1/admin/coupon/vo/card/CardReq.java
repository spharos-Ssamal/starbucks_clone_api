package com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CardReq {

    private CardReq () {
        throw new IllegalStateException("VO Class");
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateCardReq {
        private String name;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateCardReq {

        private Long cardId;
        private String name;
    }

}
