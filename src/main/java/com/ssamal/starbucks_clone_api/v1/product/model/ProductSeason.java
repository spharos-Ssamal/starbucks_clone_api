package com.ssamal.starbucks_clone_api.v1.product.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_season")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSeason extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;
}
