package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.EGiftCardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.EGiftCard;
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
public class EGiftCardRes {
    private Long id;
    private int amountPoint;
    private String defaultCard;
    private Card card;
}
