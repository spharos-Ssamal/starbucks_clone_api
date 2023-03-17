package com.ssamal.starbucks_clone_api.v1.coupon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(name = "card")
@NoArgsConstructor
@Builder
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "card_name", columnDefinition = "varchar(30) not null")
    private String name;

    @Column(name = "card_category", columnDefinition = "varchar(15) not null")
    private String category;


    public void updateNameAndCategory(String name, String category){
        this.name = name;
        this.category = category;
    }

}
