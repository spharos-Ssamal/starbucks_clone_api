package com.ssamal.starbucks_clone_api.v1.admin.coupon.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.CreateCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.CouponReq.UpdateCouponReq;
import com.ssamal.starbucks_clone_api.v1.user.coupon.dto.CouponDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import com.ssamal.starbucks_clone_api.v1.admin.coupon.service.CouponAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "[어드민] 쿠폰", description = "쿠폰 어드민 API 문서입니다.")
@RestController
@RequestMapping("/api/v1/admin/coupon")
@RequiredArgsConstructor
public class CouponAdminController {

    private final CouponAdminService couponAdminService;

    @Operation(summary = "쿠폰 생성", description = "쿠폰 생성 API 입니다.")
    @PostMapping("/create")
    public ResponseEntity<BaseRes<Long>> installCoupon(@RequestBody CreateCouponReq req){
        Long res = couponAdminService.createCoupon(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "쿠폰 조회", description = "쿠폰 조회 API 입니다.")
    @GetMapping("/getList")
    public ResponseEntity<BaseRes<List<CouponDTO>>> getCardList(){
        List<CouponDTO> couponDTOList = couponAdminService.couponList();
        return ResponseEntity.ok().body(BaseRes.success(couponDTOList));
    }

    @Operation(summary = "쿠폰 수정", description = "쿠폰 수정 API 입니다.")
    @PostMapping("/update")
    public ResponseEntity<BaseRes<Long>> updateCoupon(@RequestBody UpdateCouponReq req){
        Long couponId = couponAdminService.updateCoupon(req);
        return ResponseEntity.ok().body(BaseRes.success(couponId));
    }

    @Operation(summary = "쿠폰 삭제", description = "쿠폰 삭제 API 입니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<BaseRes<Long>> deleteCoupon(@RequestParam Long id){
        Long answer = couponAdminService.deleteCoupon(id);
        return ResponseEntity.ok().body(BaseRes.success(answer));
    }
}
