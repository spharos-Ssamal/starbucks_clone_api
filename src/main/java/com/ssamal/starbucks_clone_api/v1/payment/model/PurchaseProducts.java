package com.ssamal.starbucks_clone_api.v1.payment.model;

import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "purchase_products")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PurchaseHistory purchaseHistory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;
}
