package com.ssamal.starbucks_clone_api.v1.service.coupon.model;


import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.InsertEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.card.EGiftCardReq.UpdateEGiftCardReq;
import com.ssamal.starbucks_clone_api.v1.service.user.model.ServiceUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "e_gift")
public class EGiftCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount_point")
    private Integer amountPoint;

    @Column(name = "is_default")
    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ServiceUser serviceUser;

    public static EGiftCard of(Card card, ServiceUser serviceUser, InsertEGiftCardReq req) {
        return EGiftCard.builder()
            .card(card)
            .serviceUser(serviceUser)
            .isDefault(req.getIsDefault())
            .amountPoint(req.getAmountPoint())
            .build();
    }

    public void deleteEGiftCard() {
        this.setDeleted(true);
    }

    public void updateEgiftCard(UpdateEGiftCardReq req) {
        if (req.getPoint() != null) {
            this.amountPoint = req.getPoint();
        }

        if (req.getIsDefault() != null) {
            this.setIsDefault(req.getIsDefault());
        }
    }

}
