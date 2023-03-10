package com.ssamal.starbucks_clone_api.v1.product.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;
}
