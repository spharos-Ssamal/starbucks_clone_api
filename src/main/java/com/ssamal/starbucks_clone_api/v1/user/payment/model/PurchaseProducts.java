package com.ssamal.starbucks_clone_api.v1.user.payment.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseImmutableEntity;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "purchase_products")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseProducts extends BaseImmutableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PurchaseHistory purchaseHistory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "count")
    private Integer count;

    public static PurchaseProducts of (Product product, PurchaseHistory purchaseHistory, int count) {
        return PurchaseProducts.builder()
            .product(product)
            .purchaseHistory(purchaseHistory)
            .count(count)
            .build();
    }
}
