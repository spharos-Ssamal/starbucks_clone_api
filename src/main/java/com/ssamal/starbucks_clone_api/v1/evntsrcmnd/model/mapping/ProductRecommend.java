package com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.mapping;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.Recommend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_recommend")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRecommend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Recommend recommend;
}
