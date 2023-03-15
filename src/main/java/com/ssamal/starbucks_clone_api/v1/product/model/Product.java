package com.ssamal.starbucks_clone_api.v1.product.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import com.ssamal.starbucks_clone_api.v1.product.enums.ProductStatus;
import com.ssamal.starbucks_clone_api.v1.product.enums.Season;
import com.ssamal.starbucks_clone_api.v1.product.enums.Size;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "product")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(30) NOT NULL")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("ON_SALE")
    private ProductStatus status;

    @Column(name = "size")
    @Enumerated(value = EnumType.STRING)
    private Size size;

    @Column(name = "season")
    @Enumerated(value = EnumType.STRING)
    private Season season;

    public static Product of(ProductDTO dto){
        return ModelMapperUtils.getModelMapper().map(dto, Product.class);
    }

}
