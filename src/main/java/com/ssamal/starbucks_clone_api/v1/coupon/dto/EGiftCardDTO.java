package com.ssamal.starbucks_clone_api.v1.coupon.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.EGiftCard;

import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EGiftCardDTO {
    private Long id;
    private int amountPoint;
    private String defaultCard;
    private Card card;
    private ServiceUser serviceUser;

    public static EGiftCardDTO of(EGiftCard egiftCard){
        return ModelMapperUtils.getModelMapper().map(egiftCard, EGiftCardDTO.class);
    }

}
