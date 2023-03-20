package com.ssamal.starbucks_clone_api.v1.category.model;

import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.category.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recommend")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recommend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    @Column(name = "detail_image")
    private String detailImage;

    @Column(name = "banner_image")
    private String bannerImage;

    @Column(name = "linked_url")
    private String linkedUrl;

    @Column(name = "banner_viewable")
    @Default
    private Boolean bannerViewable = false;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public void chgViewable() {
        this.bannerViewable = !this.bannerViewable;
    }

}
