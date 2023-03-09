package com.ssamal.starbucks_clone_api.v1.user.entity;
import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ship_address")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "alias", columnDefinition = "VARCHAR(10) NOT NULL")
    private String alias;

    @Column(name = "recipient", columnDefinition = "VARCHAR(10) NOT NULL")
    private String recipient;

    @Column(name = "zip_code")
    private Long zipCode;

    @Column(name = "base_address", columnDefinition = "VARCHAR(50) NOT NULL")
    private String baseAddress;

    @Column(name = "detail_address", columnDefinition = "VARCHAR(50) NOT NULL")
    private String detailAddress;

    @Column(name = "contact_info_1", columnDefinition = "VARCHAR(12) NOT NULL")
    private String contactInfo1;

    @Column(name = "contact_info_2", columnDefinition = "VARCHAR(12)")
    private String contactInfo2;

    @Column(name = "shipping_memo", columnDefinition = "VARCHAR(50)")
    private String shippingMemo;

    @Column(name = "is_default")
    private boolean isDefaultAddress = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ServiceUser serviceUser;

}