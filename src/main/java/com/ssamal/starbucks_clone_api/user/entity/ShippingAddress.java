package com.ssamal.starbucks_clone_api.user.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ship_address")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "is_default")
    private boolean isDefaultAddress = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ServiceUser serviceUser;

}