package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.mapping;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Event;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_event")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEvent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Event event;

}
