package com.ssamal.starbucks_clone_api.v1.payment.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.global.utils.StringUtils;
import com.ssamal.starbucks_clone_api.v1.payment.dto.vo.PaymentReq.PurchasedInfo;
import com.ssamal.starbucks_clone_api.v1.payment.enums.PaymentMethod;
import com.ssamal.starbucks_clone_api.v1.payment.enums.ShippingStatus;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.ShippingAddress;
import jakarta.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.*;

@Entity
@Table(name = "purchase_history")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseHistory extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    private String id = StringUtils.generatePurchaseCode();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private ServiceUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private ShippingAddress shippingAddress;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "shipping_status")
    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    @Column(name = "shipping_message")
    private String message;
    @Column(name = "recipient")
    private String recipient;

    @Column(name = "isCanceled")
    private Boolean isCanceled;

    @Column(name = "purchase_price")
    private Integer purchasePrice;

    @Column(name = "discount_price")
    private Integer discountPrice;

    @Column(name = "total_price")
    private Integer totalPrice;

    public void calculatePriceConfirm(AtomicInteger purchasePrice, Integer discountPrice, AtomicInteger totalPrice){
        this.purchasePrice = purchasePrice.get();
        this.discountPrice = discountPrice;
        this.totalPrice = totalPrice.get();
    }

    public static PurchaseHistory of(ServiceUser user, ShippingAddress address, PurchasedInfo req){
        return PurchaseHistory.builder()
            .user(user)
            .shippingAddress(address)
            .paymentMethod(req.getMethod())
            .shippingStatus(ShippingStatus.PRODUCT_READY)
            .recipient(req.getRecipient())
            .message(req.getMessage())
            .build();
    }

}
