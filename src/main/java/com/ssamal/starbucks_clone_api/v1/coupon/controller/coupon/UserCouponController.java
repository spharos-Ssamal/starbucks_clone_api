package com.ssamal.starbucks_clone_api.v1.coupon.controller.coupon;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.UserCouponReq;
import com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.coupon.UserCouponRes;
import com.ssamal.starbucks_clone_api.v1.coupon.service.UserCouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "쿠폰",description = "쿠폰 관련 API 입니다.")
@RestController
@RequestMapping("/api/admin/v1/usercoupon")
@RequiredArgsConstructor
public class UserCouponController {
    private final UserCouponService userCouponService;
    @Operation(summary = "유저 쿠폰 등록", description = "유저 코폰 등록 API 입니다.")
    @PostMapping("/insert")
    public ResponseEntity<BaseRes<Long>> userCouponInsert(@RequestBody UserCouponReq userCouponReq){
        Long res = userCouponService.insertUserCoupon(userCouponReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }
    @Operation(summary = "유저 쿠폰 리스트", description = "유저 쿠폰 리스트 API 입니다.")
    @GetMapping("/getList")
    public ResponseEntity<BaseRes<List<UserCouponRes>>> userCouponDTOList(
            @RequestParam(name = "userId", defaultValue = "") UUID userID){
        List<UserCouponRes> userCouponResList = userCouponService.UserCouponList(userID);
        return ResponseEntity.ok().body(BaseRes.success(userCouponResList));
    }
    @Operation(summary = "유저 쿠폰 업데이트", description = "유저 쿠폰 엡데이트 API 입니다.")
    @PutMapping("/update")
    public Long userCouponUpdate(@RequestParam(name = "couponId",defaultValue = "")Long id,
                                 @RequestParam(name="status", defaultValue = "")String status){
        return userCouponService.updateUserCoupon(id, status);
    }
    @Operation(summary = "유저 쿠폰 삭제", description = "유저 삭제 API 입니다.")
    @PutMapping("/delete")
    public String userCouponDelete(@RequestParam(name = "id", defaultValue = "")Long id){
        return userCouponService.deleteUserCoupon(id);
    }

}
