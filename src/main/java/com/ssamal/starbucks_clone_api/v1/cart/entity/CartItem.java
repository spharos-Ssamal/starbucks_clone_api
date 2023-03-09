package com.ssamal.starbucks_clone_api.v1.cart.entity;

import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cart_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ServiceUser user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public void updateCountValue(int count){
        this.count = count;
    }

}
