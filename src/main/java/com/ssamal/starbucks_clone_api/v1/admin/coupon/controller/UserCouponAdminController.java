package com.ssamal.starbucks_clone_api.v1.admin.coupon.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponReq.InsertUserCouponReq;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.service.UserCouponAdminService;
import com.ssamal.starbucks_clone_api.v1.admin.coupon.vo.coupon.UserCouponRes.UserCouponList;
import com.ssamal.starbucks_clone_api.v1.coupon.enums.CouponStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "[어드민] 유저 쿠폰", description = "유저 쿠폰 관련 어드민 API 입니다.")
@RestController
@RequestMapping("/api/v1/admin/user/coupon")
@RequiredArgsConstructor
public class UserCouponAdminController {

    private final UserCouponAdminService userCouponAdminService;

    @Operation(summary = "유저 쿠폰 등록", description = "유저 코폰 등록 API 입니다.")
    @PostMapping("/insert")
    public ResponseEntity<BaseRes<Long>> userCouponInsert(
        @RequestBody InsertUserCouponReq userCouponReq) {
        Long res = userCouponAdminService.insertUserCoupon(userCouponReq);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 쿠폰 리스트", description = "유저 쿠폰 리스트 API 입니다.")
    @GetMapping("/getList")
    public ResponseEntity<BaseRes<UserCouponList>> userCouponDTOList(
        @RequestParam(name = "userId", defaultValue = "") UUID userID) {
        UserCouponList userCouponResList = userCouponAdminService.userCouponList(userID);
        return ResponseEntity.ok().body(BaseRes.success(userCouponResList));
    }

    @Operation(summary = "유저 쿠폰 업데이트", description = "유저 쿠폰 엡데이트 API 입니다.")
    @PutMapping("/update")
    public ResponseEntity<BaseRes<Long>> userCouponUpdate(
        @RequestParam(name = "couponId", defaultValue = "") Long id,
        @RequestParam(name = "status", defaultValue = "") CouponStatus status) {
        return ResponseEntity.ok()
            .body(BaseRes.success(userCouponAdminService.updateUserCoupon(id, status)));
    }

    @Operation(summary = "유저 쿠폰 삭제", description = "유저 삭제 API 입니다.")
    @PutMapping("/delete")
    public ResponseEntity<BaseRes<Long>> userCouponDelete(
        @RequestParam(name = "id", defaultValue = "") Long id) {
        return ResponseEntity.ok()
            .body(BaseRes.success(userCouponAdminService.deleteUserCoupon(id)));
    }

}
