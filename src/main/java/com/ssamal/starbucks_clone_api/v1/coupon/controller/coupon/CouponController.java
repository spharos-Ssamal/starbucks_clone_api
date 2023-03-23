package com.ssamal.starbucks_clone_api.v1.coupon.controller.coupon;

import com.ssamal.starbucks_clone_api.v1.coupon.dto.CardDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.CouponDTO;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card.CardReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.CouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.service.CardService;
import java.util.List;

import com.ssamal.starbucks_clone_api.v1.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/v1")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/coupon/install")
    public Long installCoupon(@RequestBody CouponDTO couponDTO){
        Long res = couponService.createCoupon(couponDTO);
        return res;
    }

    @GetMapping("/coupon/getList")
    public List<CouponDTO> getCardList(){
        List<CouponDTO> couponDTOList = couponService.couponList();
        return couponDTOList;
    }

    @PostMapping("/coupon/update")
    public Long updateCoupon(@RequestBody CouponReq req){
        Long couponId = couponService.updateCoupon(req);
        return couponId;
    }

    @DeleteMapping("/coupon/delete")
    public String deleteCoupon(@RequestParam Long id){
        String answer = couponService.deleteCoupon(id);
        return answer;
    }
}
