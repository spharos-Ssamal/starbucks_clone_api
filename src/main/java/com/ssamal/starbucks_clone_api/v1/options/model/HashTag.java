package com.ssamal.starbucks_clone_api.v1.options.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hashtag")
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
}
