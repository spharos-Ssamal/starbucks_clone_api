package com.ssamal.starbucks_clone_api.v1.product.model.mapping;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.product.model.HashTag;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HashTag hashTag;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Product product;

    public static ProductHashTag of(Product product, HashTag hashTag){
        return ProductHashTag.builder()
            .product(product)
            .hashTag(hashTag)
            .build();
    }

}
