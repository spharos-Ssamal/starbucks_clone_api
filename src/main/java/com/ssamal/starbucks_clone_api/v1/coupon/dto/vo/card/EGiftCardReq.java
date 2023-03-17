package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card;

import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EGiftCardReq {

    private Long id;
    private UUID user_id;
    private Long card_id;
    private int amountPoint;
    private String defaultCard;
    private int balance;


}
