package com.ssamal.starbucks_clone_api.v1.admin.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.service.UserCouponAdminService;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponReq.InsertUserCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponRes.UserCouponList;
import com.ssamal.starbucks_clone_api.v1.service.coupon.dto.UserCouponDTO;
import com.ssamal.starbucks_clone_api.v1.service.coupon.enums.CouponStatus;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.Coupon;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.UserCoupon;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.repository.CouponRepository;
import com.ssamal.starbucks_clone_api.v1.service.coupon.model.repository.UserCouponRepository;
import com.ssamal.starbucks_clone_api.v1.service.user.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.service.user.model.repository.ServiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCouponAdminServiceImpl implements UserCouponAdminService {

    private final ServiceUserRepository serviceUserRepository;
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Override
    public Long insertUserCoupon(InsertUserCouponReq req) {
        ServiceUser serviceUser = serviceUserRepository.findById(req.getUserID())
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(req.getCouponID())
            .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));

        UserCoupon userCoupon = UserCoupon.of(coupon, serviceUser, req);
        userCouponRepository.save(userCoupon);
        return userCoupon.getId();
    }

    @Override
    public UserCouponList userCouponList(UUID userID) {
        List<UserCoupon> userCouponList = userCouponRepository.findByServiceUserId(userID);
        return new UserCouponList(userCouponList.stream().map(UserCouponDTO::of).toList());
    }

    @Override
    public Long updateUserCoupon(Long id, CouponStatus status) {
        UserCoupon userCoupon = userCouponRepository.findById(id)
            .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));

        userCoupon.updateCouponStatus(status);
        userCouponRepository.save(userCoupon);
        return userCoupon.getId();
    }

    @Override
    public Long deleteUserCoupon(Long id) {
        UserCoupon userCoupon = userCouponRepository.findById(id)
            .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));
        userCoupon.deleteUserCoupon();
        userCouponRepository.save(userCoupon);
        return userCoupon.getId();
    }

}


















