package com.ssamal.starbucks_clone_api.v1.user.coupon.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.coupon.model.EGiftCard;

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
    private Boolean isDefault;

    public static EGiftCardDTO of(EGiftCard egiftCard){
        return ModelMapperUtils.getModelMapper().map(egiftCard, EGiftCardDTO.class);
    }

}
