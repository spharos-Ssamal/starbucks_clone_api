package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.CouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Coupon;
import com.ssamal.starbucks_clone_api.v1.coupon.repository.CouponRepository;
import com.ssamal.starbucks_clone_api.v1.coupon.service.CouponService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public Long createCoupon(CouponDTO couponDTO) {
        Optional<Coupon> coupon = couponRepository.findByCouponIdAndIsDeleted(couponDTO.getId(), false);
        Coupon newCoupon;
        if(coupon.isPresent()){
            newCoupon = coupon.get();
            newCoupon.updateCoupon(couponDTO.getName(), couponDTO.getPrice(), couponDTO.getCompany());
        }else{
            newCoupon = Coupon.builder()
                .name(couponDTO.getName())
                .price(couponDTO.getPrice())
                .company(couponDTO.getCompany())
                .build();
        }
        couponRepository.save(newCoupon);
        return newCoupon.getId();
    }

    @Override
    public List<CouponDTO> couponList() {
        List<Coupon>couponList = couponRepository.findAll();
        return couponList.stream().map(t -> CouponDTO.builder()
            .id(t.getId())
            .name(t.getName())
            .company(t.getCompany())
            .build()).toList();
    }

    @Override
    public Long updateCoupon(CouponReq couponReq) {
        Coupon coupon = couponRepository.findById(couponReq.getId())
            .orElseThrow(() -> new CustomException(ResCode.COUPON_NOT_FOUND));
        coupon.updateCoupon( couponReq.getName(), couponReq.getPrice(), couponReq.getCompany());
        couponRepository.save(coupon);
        return coupon.getId();
    }

    @Override
    public String deleteCoupon(CouponReq couponReq) {
        String answer;
        try{
            couponRepository.deleteById(couponReq.getId());
            answer = "삭제 성공";
        }catch (NotFoundException e){
            answer = "이미 삭제된 쿠폰";
        }
        return answer;
    }

}
