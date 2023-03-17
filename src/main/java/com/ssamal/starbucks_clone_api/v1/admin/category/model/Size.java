package com.ssamal.starbucks_clone_api.v1.admin.category.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "option_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Size extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    public static Size newData(String name) {
        return Size.builder()
            .name(name)
            .build();
    }
}
