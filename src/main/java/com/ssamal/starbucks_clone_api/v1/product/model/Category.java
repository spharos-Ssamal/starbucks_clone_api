package com.ssamal.starbucks_clone_api.v1.product.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    @Column(name = "type", columnDefinition = "VARCHAR(20) NOT NULL")
    private String type;
}
