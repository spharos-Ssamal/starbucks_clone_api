package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EGiftCardReq {
//    private Long id;
    private UUID user_id;
    private Long card_id;
    private Integer amountPoint;
    private String defaultCard;
}
