package com.ssamal.starbucks_clone_api.v1.coupon.entity;


import com.ssamal.starbucks_clone_api.global.entity.BaseEntity;
import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "user_coupon_list")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserCoupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "date_of_user")
    private LocalDate userDate;

    @Column(name = "expiration_date", nullable = false, updatable = false)
    private LocalDate expirationDate;

    //cascade를 걸기 위해서 onetoMany를 사용하는게 좋을 것 같네요.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ServiceUser serviceUser;

    //cascade를 걸기 위해서 onetoMany를 사용하는게 좋을 것 같네요.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

}
