package com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EGiftCardReq {

    private EGiftCardReq () {
        throw new IllegalStateException("VO Class");
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InsertEGiftCardReq {

        private UUID userId;
        private Long cardId;
        private Integer amountPoint;
        private Boolean isDefault;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateEGiftCardReq {
        private UUID userId;
        private Long id;
        private Integer point;
        private Boolean isDefault;
    }
}
