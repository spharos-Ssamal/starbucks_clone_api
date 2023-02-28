package com.ssamal.starbucks_clone_api.v1.product.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_hashtag")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductHashTag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private HashTag hashTag;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
