package com.ssamal.starbucks_clone_api.v1.coupon.service.impl;

import com.ssamal.starbucks_clone_api.global.config.modelmapper.ModelMapperConfig;
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
    private final ModelMapperConfig modelMapperConfig;
    @Override
    public Long createCoupon(CouponDTO couponDTO) {
        Coupon coupon = modelMapperConfig.modelMapper().map(couponDTO,Coupon.class);
        couponRepository.save(coupon);
        return coupon.getId();
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
        coupon.updateCoupon( couponReq.getCouponName(), couponReq.getPrice(), couponReq.getCompany());
        couponRepository.save(coupon);
        return coupon.getId();
    }
    @Override
    public String deleteCoupon(Long id) {
        String answer = "삭제 성공";
        couponRepository.findById(id)
                .orElseThrow( () -> new CustomException(ResCode.COUPON_NOT_FOUND));
        couponRepository.deleteById(id);
        return answer;
    }
}
