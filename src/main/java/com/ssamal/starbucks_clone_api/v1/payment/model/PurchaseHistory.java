package com.ssamal.starbucks_clone_api.v1.payment.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseImmutableEntity;
import com.ssamal.starbucks_clone_api.v1.payment.enums.PaymentMethod;
import com.ssamal.starbucks_clone_api.v1.payment.enums.ShippingStatus;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.ShippingAddress;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "purchase_history")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseHistory extends BaseImmutableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

}
