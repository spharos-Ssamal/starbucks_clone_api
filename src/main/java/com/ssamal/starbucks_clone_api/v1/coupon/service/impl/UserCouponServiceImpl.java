package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.config.modelmapper.ModelMapperConfig;
import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.UserCouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.UserCouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.UserCouponRes;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Coupon;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.UserCoupon;
import com.ssamal.starbucks_clone_api.v1.coupon.repository.CouponRepository;
import com.ssamal.starbucks_clone_api.v1.coupon.repository.UserCouponRepository;
import com.ssamal.starbucks_clone_api.v1.coupon.service.UserCouponService;
import com.ssamal.starbucks_clone_api.v1.user.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.model.repository.ServiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCouponServiceImpl implements UserCouponService{
    private final ServiceUserRepository serviceUserRepository;
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    @Override
    public Long insertUserCoupon(UserCouponReq userCouponReq) {
        ServiceUser serviceUser = serviceUserRepository.findById(userCouponReq.getUserID())
                .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        Coupon coupon = couponRepository.findById(userCouponReq.getCouponID())
                .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));
        UserCoupon userCoupon = UserCoupon.builder()
                .coupon(coupon)
                .serviceUser(serviceUser)
                .status(userCouponReq.getStatus())
                .expirationDate(LocalDate.now())
                .build();
        userCouponRepository.save(userCoupon);
        return userCoupon.getId();
    }
    @Override
    public List<UserCouponRes> UserCouponList(UUID userID) {
        List<UserCoupon> userCouponList = userCouponRepository.findByServiceUser(userID);
        return userCouponList.stream().map( t -> UserCouponRes.builder()
                .status(t.getStatus())
                .coupon(t.getCoupon())
                .build()).toList();
    }
    @Override
    public Long updateUserCoupon(Long id, String status) {
        UserCoupon userCoupon = userCouponRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));
        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();
        UserCouponDTO userCouponDTO = modelMapperConfig.modelMapper().map(userCoupon, UserCouponDTO.class);
        userCouponDTO.setStatus(status);
        userCoupon = modelMapperConfig.modelMapper().map(userCouponDTO,UserCoupon.class);
        userCouponRepository.save(userCoupon);
        return userCoupon.getId();
    }

    @Override
    public String deleteUserCoupon(Long id) {
        UserCoupon userCoupon = userCouponRepository.findById(id)
                .orElseThrow(()-> new CustomException(ResCode.COUPON_NOT_FOUND));
        userCouponRepository.deleteById(userCoupon.getId());
        return "삭제 성공";
    }


}


















