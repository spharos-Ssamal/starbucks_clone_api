package com.ssamal.starbucks_clone_api.v1.payment.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.global.utils.StringUtils;
import com.ssamal.starbucks_clone_api.v1.payment.dto.vo.PaymentReq.PurchasedInfo;
import com.ssamal.starbucks_clone_api.v1.payment.enums.PaymentMethod;
import com.ssamal.starbucks_clone_api.v1.payment.enums.ShippingStatus;
import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.address.model.ShippingAddress;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString.Exclude;

@Entity
@Table(name = "purchase_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseHistory extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @Default
    private String historyId = StringUtils.generatePurchaseCode();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Exclude
    private ServiceUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @Exclude
    private ShippingAddress shippingAddress;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "shipping_status")
    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    @Column(name = "isCanceled")
    private Boolean isCanceled;

    @Column(name = "shipping_fee")
    private Integer shippingFee;

    @Column(name = "purchase_price")
    private Integer purchasePrice;

    @Column(name = "discount_price")
    private Integer discountPrice;

    @Column(name = "total_price")
    private Integer totalPrice;
    public static PurchaseHistory of(ServiceUser user, ShippingAddress address, PurchasedInfo req){
        return PurchaseHistory.builder()
            .user(user)
            .shippingAddress(address)
            .paymentMethod(req.getPaymentMethod())
            .shippingStatus(ShippingStatus.PRODUCT_READY)
            .shippingFee(req.getShippingFee())
            .purchasePrice(req.getAmountOfProductPrice())
            .discountPrice(req.getAmountOfDiscount())
            .totalPrice(req.getAmountOfTotalPrice())
            .build();
    }
}
