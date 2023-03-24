package com.ssamal.starbucks_clone_api.v1.coupon.model;


import com.ssamal.starbucks_clone_api.global.entity.BaseTimeEntity;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponReq.InsertUserCouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.enums.CouponStatus;
import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_coupon_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCoupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    @Column(name = "date_of_use")
    private LocalDate useDate;

    @Column(name = "expiration_date", nullable = false, updatable = false)
    private LocalDate expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ServiceUser serviceUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    public void updateCouponStatus(CouponStatus status) {
        this.status = status;
    }
    public void deleteUserCoupon() {
        this.setDeleted(true);
    }

    public static UserCoupon of(Coupon coupon, ServiceUser user, InsertUserCouponReq req) {
        return UserCoupon.builder()
            .coupon(coupon)
            .serviceUser(user)
            .status(CouponStatus.ACTIVE)
            .expirationDate(req.getExpirationDate())
            .build();
    }

}
