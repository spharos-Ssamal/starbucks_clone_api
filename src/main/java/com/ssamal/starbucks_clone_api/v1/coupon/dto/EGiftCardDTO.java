package com.ssamal.starbucks_clone_api.v1.coupon.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.EGiftCard;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private int balance;
    private Card card;
    private ServiceUser serviceUser;

    public static EGiftCardDTO of(EGiftCard egiftCard){
        return ModelMapperUtils.getModelMapper().map(egiftCard, EGiftCardDTO.class);
    }

}